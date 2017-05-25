package es.juanlsanchez.datask.manager.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.manager.IncomeManager;
import es.juanlsanchez.datask.mapper.IncomeMapper;
import es.juanlsanchez.datask.service.IncomeService;
import es.juanlsanchez.datask.util.StatisticsUtil;
import es.juanlsanchez.datask.web.dto.IncomeDTO;
import es.juanlsanchez.datask.web.dto.RangeDTO;
import javassist.NotFoundException;

@Component
public class DefaultIncomeManager implements IncomeManager {

  private final IncomeMapper incomeMapper;
  private final IncomeService incomeService;
  private final StatisticsUtil statisticsUtil;

  @Inject
  public DefaultIncomeManager(final IncomeMapper incomeMapper, final IncomeService incomeService,
      final StatisticsUtil statisticsUtil) {
    this.incomeMapper = incomeMapper;
    this.incomeService = incomeService;
    this.statisticsUtil = statisticsUtil;
  }

  @Override
  public Page<IncomeDTO> findAllByPrincipal(Pageable pageable) {
    return incomeService.findAllByPrincipal(pageable)
        .map(income -> incomeMapper.incomeToIncomeDTO(income));
  }

  @Override
  public Optional<IncomeDTO> findOne(Long id) {
    return incomeService.findOne(id).map(income -> incomeMapper.incomeToIncomeDTO(income));
  }

  @Override
  public IncomeDTO create(IncomeDTO income) {
    return incomeMapper
        .incomeToIncomeDTO(incomeService.create(incomeMapper.incomeDTOToIncome(income)));
  }

  @Override
  public IncomeDTO update(IncomeDTO income, Long incomeId) throws NotFoundException {
    return incomeMapper
        .incomeToIncomeDTO(incomeService.update(incomeMapper.incomeDTOToIncome(income), incomeId));
  }

  @Override
  public void delete(Long id) throws NotFoundException {
    this.incomeService.delete(id);
  }

  @Override
  public RangeDTO getRangeByPrincipal() {
    return this.incomeService.getRangeByPrincipal();
  }


  @Override
  public Map<LocalDate, Double> evolutionInDaysInTheRange(LocalDate start, LocalDate end) {
    return statisticsUtil.fillInterval(this.incomeService.evolutionInDaysInTheRange(start, end),
        start, end, 0.0, 1, ChronoUnit.DAYS);
  }

}
