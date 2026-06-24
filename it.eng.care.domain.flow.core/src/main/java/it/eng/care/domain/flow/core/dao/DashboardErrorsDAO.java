package it.eng.care.domain.flow.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.eng.care.domain.flow.core.entity.DashboardErrorsDO;
import jakarta.transaction.Transactional;

public interface DashboardErrorsDAO extends JpaRepository<DashboardErrorsDO, String> {

    List<DashboardErrorsDO> findAllByFlowNameAndMonthAndYear(String flowName, String month, String year);

    List<DashboardErrorsDO> findAllByMonthBetweenAndFlowNameAndYear(String monthFrom, String monthTo, String flow, String year);
    
    List<DashboardErrorsDO> findAllByFlowNameAndCodiceAziendaIsNull(String flowName);
    
    List<DashboardErrorsDO> findAllByFlowNameAndMonthAndYearAndCodiceAziendaIn(String flowName, String month, String year, List<String> aziende);

    List<DashboardErrorsDO> findAllByMonthBetweenAndFlowNameAndYearAndCodiceAziendaIn(String monthFrom, String monthTo, String flow, String year, List<String> aziende);
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query("""
	    delete from DashboardErrorsDO d
	    where d.flowName = :flow
	""")
	int deleteByFlowEquals(@Param("flow") String flow);
    
  //flowName + (between OR null)
	  @Query("""
	    select d
	    from DashboardErrorsDO d
	    where d.flowName = :flowName
	      and ( (d.day between :from and :to) or d.day is null )
	  """)
	  List<DashboardErrorsDO> findByFlowNameAndDateBetweenOrNull(
	      @Param("from") Date dataDa,
	      @Param("to") Date dataA,
	      @Param("flowName") String flowName
	  );

	  //flowName + aziende + (between OR null)
	  @Query("""
	    select d
	    from DashboardErrorsDO d
	    where d.flowName = :flowName
	      and d.codiceAzienda in :aziende
	      and ( (d.day between :from and :to) or d.day is null )
	  """)
	  List<DashboardErrorsDO> findByFlowNameAziendeAndDateBetweenOrNull(
	      @Param("from") Date dataDa,
	      @Param("to") Date dataA,
	      @Param("flowName") String flowName,
	      @Param("aziende") List<String> aziende
	  );
    
}
