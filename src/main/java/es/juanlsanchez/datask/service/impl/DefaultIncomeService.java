package es.juanlsanchez.datask.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.juanlsanchez.datask.domain.Income;
import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.mapper.IncomeMapper;
import es.juanlsanchez.datask.repository.IncomeRepository;
import es.juanlsanchez.datask.service.IncomeService;
import es.juanlsanchez.datask.service.UserService;
import es.juanlsanchez.datask.util.object.Pair;
import es.juanlsanchez.datask.web.dto.RangeDTO;
import javassist.NotFoundException;

@Service
@Transactional
public class DefaultIncomeService implements IncomeService {

  private final IncomeRepository incomeRepository;
  private final UserService userService;
  private final IncomeMapper incomeMapper;

  @Inject
  public DefaultIncomeService(final IncomeRepository incomeRepository,
      final UserService userService, final IncomeMapper incomeMapper) {
    this.incomeRepository = incomeRepository;
    this.userService = userService;
    this.incomeMapper = incomeMapper;
  }

  @Override
  public Page<Income> findAllByPrincipal(Pageable pageable) {
    return incomeRepository.findAllByPrincipal(pageable);
  }

  @Override
  public Optional<Income> findOne(Long id) {
    User principal = userService.getPrincipal();
    return incomeRepository.findOneByIdAndPrincipal(id, principal);
  }

  @Override
  public Income create(Income income) {
    User principal = userService.getPrincipal();
    income.setPrincipal(principal);

    Income result = incomeRepository.save(income);

    return result;
  }

  @Override
  public Income update(Income income, Long incomeId) throws NotFoundException {
    Income incomeTarget = this.findOne(incomeId)
        .orElseThrow(() -> new NotFoundException("Not found the income " + incomeId));

    incomeMapper.updateIncome(income, incomeTarget);

    Income result = incomeRepository.save(incomeTarget);
    return result;
  }

  @Override
  public void delete(Long id) throws NotFoundException {
    this.findOne(id).orElseThrow(() -> new NotFoundException("Not found the income " + id));
    incomeRepository.delete(id);
  }

  @Override
  public RangeDTO getRangeByPrincipal() {
    User principal = this.userService.getPrincipal();
    return this.incomeRepository.getRangeByPrincipal(principal);
  }

  @Override
  public List<Income> findAllByPrincipalAndIncomeDateGreaterThanEqualAndIncomeDateLessThan(
      LocalDate start, LocalDate finish) {
    User principal = this.userService.getPrincipal();
    return this.incomeRepository
        .findAllByPrincipalAndIncomeDateGreaterThanEqualAndIncomeDateLessThanOrderByIncomeDateAsc(
            principal, start, finish);
  }

  @Override
  public Map<LocalDate, Double> evolutionInDaysInTheRange(LocalDate start, LocalDate end) {
    return this.incomeRepository.evolutionInDaysInTheRange(start, end).stream()
        .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
  }

}
