package net.visionvalley.iot.smac.atemanagement.domains;



import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ate_state_history")
@JsonIgnoreProperties
public class ATEHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ate_history_generator")
	@TableGenerator(name="ate_history_generator", table="id_generator", schema="history")
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	@Column(name="ATE_TAG_NO")
	private String ateTagNo;
	@Column(name="ATE_STATE")
	private int state;
	@Column(name="ATE_MAC_ADDRESS")
	private String macAddress;	
	
	@Column(name = "ATE_STATE_CHANGE", insertable = false, updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	
	private Date changeDate;
	@Column(name="ATE_EMIRATE_NAME")
	private String emirateName;
	@Column(name="ATE_AREA_CODE")
	private String areaCode;
	@Column(name="ATE_BUILDING_CLS")
	private int buildingCls;
	@Column(name="ATE_NO_INPUT")
	private int numberOfInputs;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAteTagNo() {
		return ateTagNo;
	}
	public void setAteTagNo(String ateTagNo) {
		this.ateTagNo = ateTagNo;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public String getEmirateName() {
		return emirateName;
	}
	public void setEmirateName(String emirateName) {
		this.emirateName = emirateName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public int getBuildingCls() {
		return buildingCls;
	}
	public void setBuildingCls(int buildingCls) {
		this.buildingCls = buildingCls;
	}
	public int getNumberOfInputs() {
		return numberOfInputs;
	}
	public void setNumberOfInputs(int numberOfInputs) {
		this.numberOfInputs = numberOfInputs;
	}
	
	

}
