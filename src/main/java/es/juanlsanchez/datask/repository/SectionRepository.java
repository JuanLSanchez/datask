package es.juanlsanchez.datask.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.juanlsanchez.datask.domain.Section;
import es.juanlsanchez.datask.domain.User;

public interface SectionRepository extends JpaRepository<Section, Long> {

  public Optional<Section> findOneByIdAndPrincipal(Long id, User principal);

  public List<Section> findAllByPrincipalOrderByOrderAsc(User principal);

  @Query("select section from Section section "
      + "where section.principal.login=?#{principal.username} ")
  public Page<Section> findAllByPrincipal(Pageable pageable);

}
