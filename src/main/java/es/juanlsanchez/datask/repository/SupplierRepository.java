package es.juanlsanchez.datask.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.juanlsanchez.datask.domain.Supplier;
import es.juanlsanchez.datask.domain.User;
import es.juanlsanchez.datask.util.object.Trio;

@Transactional(rollbackFor = Throwable.class)
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

  @Query("select supplier from Supplier supplier "
      + "where supplier.principal.login=?#{principal.username} ")
  public Page<Supplier> findAllByPrincipal(Pageable pageable);

  public Optional<Supplier> findOneByIdAndPrincipal(Long id, User principal);

  @Query("select new es.juanlsanchez.bm.util.object.Trio(invoiceLine.invoice.supplier, invoiceLine.invoice.dateBuy, sum(invoiceLine.base)) "//
      + "from InvoiceLine invoiceLine where "//
      + "   invoiceLine.invoice.dateBuy >= :start "//
      + "   and invoiceLine.invoice.dateBuy < :end "//
      + "group by invoiceLine.invoice.dateBuy, invoiceLine.invoice.supplier")
  public List<Trio<Supplier, LocalDate, Double>> evolutionInDaysInTheRange(
      @Param("start") LocalDate start, @Param("end") LocalDate end);

}
