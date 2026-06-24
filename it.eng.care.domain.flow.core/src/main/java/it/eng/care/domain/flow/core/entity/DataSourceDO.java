package it.eng.care.domain.flow.core.entity;

import it.eng.care.platform.persistence.api.IBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "FM_DATA_SOURCE")
public class DataSourceDO implements IBaseEntity {

    @Id
    @GeneratedValue(
            generator = "care-uuid"
    )
    @GenericGenerator(
            name = "care-uuid",
            strategy = "it.eng.care.platform.persistence.impl.jpa.idgenerator.CareIdGenerator"
    )
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "HOSTNAME")
    private String hostname;
    @Column(name = "PORT")
    private String port;
    @Column(name = "SERVICE_NAME")
    private String serviceName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DRIVER")
    private DriverDO driver;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public DriverDO getDriver() {
        return driver;
    }

    public void setDriver(DriverDO driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
