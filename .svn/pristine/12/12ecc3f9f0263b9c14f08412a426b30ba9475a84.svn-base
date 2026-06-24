package it.eng.care.platform.tool.audit;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.event.spi.AbstractPreDatabaseOperationEvent;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import it.eng.care.domain.flow.core.utility.LogUtil;
import it.eng.care.platform.authentication.api.model.bean.LoggedUser;
import it.eng.care.platform.authentication.api.service.LoggedUserService;
import it.eng.care.platform.common.naming.HasAuthor;
import it.eng.care.platform.common.util.GlobalDatastore;
import it.eng.care.platform.persistence.api.period.HasPeriod;
import it.eng.care.platform.persistence.impl.jpa.AbstractAuditableEntity;
import it.eng.care.platform.persistence.impl.jpa.MultiTenantEntityDO;

/**
 * The listener interface for receiving auditEntity events. The class that is
 * interested in processing a auditEntity event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addAuditEntityListener<code> method. When the auditEntity
 * event occurs, that object's appropriate method is invoked.
 *
 *
 */
public class HibernateEntityListener implements PreInsertEventListener, PreUpdateEventListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5717042281120390025L;

	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateEntityListener.class);

	/** The logged user service. */
	@Autowired
	private transient LoggedUserService loggedUserService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.hibernate.event.spi.PreUpdateEventListener#onPreUpdate(org.hibernate.
	 * event.spi.PreUpdateEvent)
	 */
	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		if (event.getEntity() instanceof AbstractAuditableEntity) {
			computeState(event);
		}
		if (event.getEntity() instanceof HasAuthor) {
			HasAuthor e = (HasAuthor) event.getEntity();
			if (StringUtils.isBlank(e.getAuthor())) {
				e.setAuthor(getAuthor());
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.hibernate.event.spi.PreInsertEventListener#onPreInsert(org.hibernate.
	 * event.spi.PreInsertEvent)
	 */
	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		if (event.getEntity() instanceof AbstractAuditableEntity) {
			AbstractAuditableEntity e = (AbstractAuditableEntity) event.getEntity();
			e.setCreatedBy(getUserName());
			e.setSessionCreated(getSessionId());
			e.setCreatedDate(new Date());
		}

		if (event.getEntity() instanceof HasAuthor) {
			HasAuthor e = (HasAuthor) event.getEntity();
			if (StringUtils.isBlank(e.getAuthor())) {
				e.setAuthor(getAuthor());
			}
		}

		if (event.getEntity() instanceof HasPeriod) {
			HasPeriod<?> e = (HasPeriod<?>) event.getEntity();
			if (e.getStartDate() == null) {
				e.setStartDate(new Date());
				}
		}

		if (event.getEntity() instanceof MultiTenantEntityDO) {
			MultiTenantEntityDO entity = (MultiTenantEntityDO) event.getEntity();
			if (entity.getOrganizationCode() == null && loggedUserService != null
					&& loggedUserService.getCurrentUser() != null
					&& loggedUserService.getCurrentUser().getCurrentOrganization() != null) {
				entity.setOrganizationCode(loggedUserService.getCurrentUser().getCurrentOrganization().getCode());
			}
		}

		updateInsertState(event, event.getState());

		return false;
	}

	private void computeState(PreUpdateEvent event) {
		AbstractAuditableEntity entity = (AbstractAuditableEntity) event.getEntity();
		Object[] oldState = event.getOldState();

		entity.setLastModifiedBy(getUserName());
		entity.setLastModifiedDate(new Date());
		entity.setSessionModified(getSessionId());

		recallInsertState(event, entity, oldState);

		if (event.getEntity() instanceof MultiTenantEntityDO) {
			recallMultitenantState(event, (MultiTenantEntityDO) entity, oldState);
		}

		computeDeleteState(entity);



	}

	private void recallInsertState(PreUpdateEvent event, AbstractAuditableEntity entity, Object[] oldState) {
		int createdByIndex = ArrayUtils.indexOf(event.getPersister().getEntityMetamodel().getPropertyNames(),
				"createdBy");
		if (createdByIndex >= 0 && oldState[createdByIndex] != null && entity.getCreatedBy() == null) {
			entity.setCreatedBy((String) oldState[createdByIndex]);
		}
		int createdDateIndex = ArrayUtils.indexOf(event.getPersister().getEntityMetamodel().getPropertyNames(),
				"createdDate");
		if (createdDateIndex >= 0 && oldState[createdDateIndex] != null && entity.getCreatedDate() == null) {
			entity.setCreatedDate((Date) oldState[createdDateIndex]);
		}
		int sessionCreatedIndex = ArrayUtils.indexOf(event.getPersister().getEntityMetamodel().getPropertyNames(),
				"sessionCreated");
		if (sessionCreatedIndex >= 0 && oldState[sessionCreatedIndex] != null && entity.getSessionCreated() == null) {
			entity.setSessionCreated((String) oldState[sessionCreatedIndex]);
		}
		updateInsertState(event, event.getState());
	}

	private void recallMultitenantState(PreUpdateEvent event, MultiTenantEntityDO entity, Object[] oldState) {
		int organizationCodeIndex = ArrayUtils.indexOf(event.getPersister().getEntityMetamodel().getPropertyNames(),
				"organizationCode");
		if (organizationCodeIndex >= 0 && oldState[organizationCodeIndex] != null
				&& entity.getOrganizationCode() == null) {
			entity.setOrganizationCode((String) oldState[organizationCodeIndex]);
		} else {
			if (entity.getOrganizationCode() == null && loggedUserService != null
					&& loggedUserService.getCurrentUser() != null
					&& loggedUserService.getCurrentUser().getCurrentOrganization() != null) {
				entity.setOrganizationCode(loggedUserService.getCurrentUser().getCurrentOrganization().getCode());
			}
		}
	}

	private void computeDeleteState(AbstractAuditableEntity entity) {
		if (entity.isDeleted() && entity.getDeletedBy() == null && entity.getDeletionDate() == null) {
			entity.setDeletedBy(getUserName());
			entity.setDeletionDate(new Date());
			entity.setSessionDeleted(getSessionId());
		}
	}
	
	private void updateInsertState(AbstractPreDatabaseOperationEvent event, Object[] state) {
	    Object entity = event.getEntity();

	    // evita classi proxy/hibernate enhancer: se è una subclass “$$…”, usa la superclass
	    Class<?> entityClass = entity.getClass();
	    if (entityClass.getName().contains("$$") && entityClass.getSuperclass() != null) {
	        entityClass = entityClass.getSuperclass();
	    }

	    String[] propertyNames = event.getPersister().getEntityMetamodel().getPropertyNames();
	    BeanWrapper bw = new BeanWrapperImpl(entity);

	    for (int i = 0; i < propertyNames.length; i++) {
	        if (i >= state.length) continue;
	        if (state[i] != null) continue;

	        String propertyName = propertyNames[i];

	        try {
	            Object value = null;

	            if (entity instanceof Map<?, ?> map) {
	                value = map.get(propertyName);
	            } else if (bw.isReadableProperty(propertyName)) {
	                // prende da getter (gestisce anche boolean isX / getX)
	                value = bw.getPropertyValue(propertyName);
	            } else {
	                // fallback: prova field diretto
	                Field field = ReflectionUtils.findField(entityClass, propertyName);
	                if (field == null) {
	                    // fallback per casi tipo pk -> isPk
	                    field = ReflectionUtils.findField(entityClass,
	                            "is" + StringUtils.capitalize(propertyName));
	                }
	                if (field != null) {
	                    field.setAccessible(true);
	                    value = ReflectionUtils.getField(field, entity);
	                }
	            }

	            if (value != null) {
	                state[i] = value;
	            }

	        } catch (Exception e) {
	            // in audit meglio DEBUG, non ERROR: non deve “sporcare” i log
	        	LogUtil.logException(LOGGER, "Audit updateInsertState: cannot read property "+propertyName+" from "+entityClass.getName()+"", e);
	        }
	    }
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	private String getUserName() {
		if (loggedUserService != null && loggedUserService.getCurrentUser() != null) {
			return loggedUserService.getCurrentUser().getUsername();
		}
		return GlobalDatastore.NOLOGGEDUSER;
	}

	private String getSessionId() {
		if (loggedUserService != null && loggedUserService.getCurrentUser() != null) {
			return loggedUserService.getCurrentUser().getSessionId();
		}
		return "NOSESSION";
	}

	private String getAuthor() {
		if (loggedUserService != null) {
			LoggedUser currentUser = loggedUserService.getCurrentUser();
			if (currentUser != null) {
				return ObjectUtils.firstNonNull(currentUser.getCentralCode(), currentUser.getUsername(), GlobalDatastore.NOLOGGEDUSER);
			}
		}
		return GlobalDatastore.NOLOGGEDUSER;
	}
}