package es.juanlsanchez.datask.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import es.juanlsanchez.datask.domain.Invoice;
import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.web.dto.RangeDTO;

@Transactional(rollbackFor = Throwable.class)
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

  @Query("select invoice from Invoice invoice "
      + "where invoice.principal.login=?#{principal.username} ")
  public Page<Invoice> findAllByPrincipal(Pageable pageable);

  public Optional<Invoice> findOneByIdAndPrincipal(Long id, User principal);

  @Query("select new es.juanlsanchez.bm.web.dto.RangeDTO(min(invoice.dateBuy), max(invoice.dateBuy)) "
      + "from Invoice invoice where invoice.principal.login=?#{principal.username}")
  public RangeDTO getRangeByPrincipal(User principal);

  public List<Invoice> findAllByPrincipalAndDateBuyGreaterThanEqualAndDateBuyLessThanOrderByDateBuyAsc(
      User principal, LocalDate start, LocalDate finish);

}
