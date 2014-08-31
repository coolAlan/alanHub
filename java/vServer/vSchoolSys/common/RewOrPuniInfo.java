
package vSchoolSys.common;

import java.io.Serializable;
import java.sql.Date;


public class RewOrPuniInfo implements Serializable{
private int order;
private String uID;
private String uRewOrPuni;
private String uROrPName;
private String uROrPTime;
private String uReason;
private String uPlace;
public RewOrPuniInfo(String uID,String uRewOrPuni,String uROrPName,String uROrPTime,String uReason,String uPlace){
	this.uID=uID;
	this.uRewOrPuni=uRewOrPuni;
	this.uROrPName=uROrPName;
	this.uROrPTime=uROrPTime;
	this.uReason=uReason;
	this.uPlace=uPlace;
}
public RewOrPuniInfo(int o,String uID,String uRewOrPuni,String uROrPName,String uROrPTime,String uReason,String uPlace){
	this.order = o;
	this.uID=uID;
	this.uRewOrPuni=uRewOrPuni;
	this.uROrPName=uROrPName;
	this.uROrPTime=uROrPTime;
	this.uReason=uReason;
	this.uPlace=uPlace;
}
/**
 * @return Returns the uID.
 */
public String getUID() {
	return uID;
}
/**
 * @param uid The uID to set.
 */
public void setUID(String uID) {
	this.uID = uID;
}
/**
 * @return Returns the uPlace.
 */
public String getUPlace() {
	return uPlace;
}
/**
 * @param place The uPlace to set.
 */
public void setUPlace(String uPlace) {
	this.uPlace = uPlace;
}
/**
 * @return Returns the uReason.
 */
public String getUReason() {
	return uReason;
}
/**
 * @param reason The uReason to set.
 */
public void setUReason(String uReason) {
	this.uReason = uReason;
}
/**
 * @return Returns the uRewOrPuni.
 */
public String getURewOrPuni() {
	return uRewOrPuni;
}
/**
 * @param rewOrPuni The uRewOrPuni to set.
 */
public void setURewOrPuni(String uRewOrPuni) {
	this.uRewOrPuni = uRewOrPuni;
}
/**
 * @return Returns the uROrPName.
 */
public String getUROrPName() {
	return uROrPName;
}
/**
 * @param orPName The uROrPName to set.
 */
public void setUROrPName(String uROrPName) {
	this.uROrPName = uROrPName;
}
/**
 * @return Returns the uROrPTime.
 */
public String getUROrPTime() {
	return uROrPTime;
}
/**
 * @param orPTime The uROrPTime to set.
 */
public void setUROrPTime(String uROrPTime) {
	this.uROrPTime = uROrPTime;
}


/**
 * @return Returns the order.
 */
public int getOrder() {
	return order;
}
/**
 * @param order The order to set.
 */
public void setOrder(int order) {
	this.order = order;
}
}
