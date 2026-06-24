package it.eng.care.domain.flow.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.eng.care.domain.flow.core.entity.DashboardDO;
import jakarta.transaction.Transactional;

@Repository
public interface DashboardDAO  extends JpaRepository<DashboardDO, String>{

	List<DashboardDO> findByMonthBetweenAndYearEqualsAndFlowAndWidgetName(Integer monthFrom, Integer monthTo,
			Integer year, String code, String string);	
	
	List<DashboardDO> findByMonthBetweenAndYearEqualsAndFlow(Integer monthFrom, Integer monthTo,
			Integer year, String code);	
	
	List<DashboardDO> findAllByFlowAndCodiceAziendaIsNull(String flowName);
	
	List<DashboardDO> findByMonthBetweenAndYearEqualsAndFlowAndWidgetNameAndCodiceAziendaIn(Integer monthFrom, Integer monthTo,
			Integer year, String code, String string, List<String> aziende);	
	
	List<DashboardDO> findByMonthBetweenAndYearEqualsAndFlowAndCodiceAziendaIn(Integer monthFrom, Integer monthTo,
			Integer year, String code, List<String> aziende);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query("""
	    delete from DashboardDO d
	    where d.month in :months
	      and d.year  in :years
	      and d.flow = :flow
	""")
	int deleteByMonthsInAndYearsInAndFlowEquals(@Param("months") List<Integer> months,
	                              @Param("years") List<Integer> years,
	                              @Param("flow") String flow);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query("""
	    delete from DashboardDO d
	    where d.flow = :flow
	    and d.codiceAzienda is null
	""")
	int deleteByFlowAndCodiceAziendaIsNull(@Param("flow") String flow);
	
	boolean existsByFlowAndWidgetNameAndMonthAndYearAndCodiceAzienda(
		    String flow, String widgetName, Integer month, Integer year, String codiceAzienda
		);

		Optional<DashboardDO> findFirstByFlowAndWidgetNameAndMonthAndYearAndCodiceAzienda(
		    String flow, String widgetName, Integer month, Integer year, String codiceAzienda
		);
	
		//flow + widget + (between OR null)
		  @Query("""
		    select d
		    from DashboardDO d
		    where d.flow = :flow
		      and d.widgetName = :widgetName
		      and ( (d.day between :from and :to) or d.day is null )
		  """)
		  List<DashboardDO> findByFlowWidgetAndDateBetweenOrNull(
		      @Param("from") Date dataDa,
		      @Param("to") Date dataA,
		      @Param("flow") String flow,
		      @Param("widgetName") String widgetName
		  );

		  //flow + (between OR null)
		  @Query("""
		    select d
		    from DashboardDO d
		    where d.flow = :flow
		      and ( (d.day between :from and :to) or d.day is null )
		  """)
		  List<DashboardDO> findByFlowAndDateBetweenOrNull(
		      @Param("from") Date dataDa,
		      @Param("to") Date dataA,
		      @Param("flow") String flow
		  );

		  //flow + widget + aziende + (between OR null)
		  @Query("""
		    select d
		    from DashboardDO d
		    where d.flow = :flow
		      and d.widgetName = :widgetName
		      and d.codiceAzienda in :aziende
		      and ( (d.day between :from and :to) or d.day is null )
		  """)
		  List<DashboardDO> findByFlowWidgetAziendeAndDateBetweenOrNull(
		      @Param("from") Date dataDa,
		      @Param("to") Date dataA,
		      @Param("flow") String flow,
		      @Param("widgetName") String widgetName,
		      @Param("aziende") List<String> aziende
		  );

		  //flow + aziende + (between OR null)
		  @Query("""
		    select d
		    from DashboardDO d
		    where d.flow = :flow
		      and d.codiceAzienda in :aziende
		      and ( (d.day between :from and :to) or d.day is null )
		  """)
		  List<DashboardDO> findByFlowAziendeAndDateBetweenOrNull(
		      @Param("from") Date dataDa,
		      @Param("to") Date dataA,
		      @Param("flow") String flow,
		      @Param("aziende") List<String> aziende
		  );
		
	  List<DashboardDO> findAllByFlowAndMonthAndYearAndCodiceAziendaIn(
		        String flow, Integer month, Integer year, List<String> aziende);

	  List<DashboardDO> findAllByFlowAndMonthAndYearAndCodiceAziendaIsNull(
		        String flow, Integer month, Integer year);

	  List<DashboardDO> findAllByFlowAndWidgetNameAndMonthAndYearAndCodiceAziendaIn(
		        String flow, String widgetName, Integer month, Integer year, List<String> aziende);

	  List<DashboardDO> findAllByFlowAndWidgetNameAndMonthAndYearAndCodiceAziendaIsNull(
		        String flow, String widgetName, Integer month, Integer year);
}