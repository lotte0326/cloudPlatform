package org.jeecgframework.modules.system.pojo.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 权限组权限关联表
 * @author onlineGenerator
 * @date 2017-08-24 18:49:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_s_function_group_rel", schema = "")
@SuppressWarnings("serial")
public class TSFunctionGroupRelEntity implements java.io.Serializable {
	/**ID*/
	private String id;
	/**权限组ID*/
	@Excel(name="权限组ID",width=15)
	private TSFunctionGroupEntity tsFunctionGroup;
	/**权限ID*/
	@Excel(name="权限ID",width=15)
	private TSFunction tsFunction;
	/**页面操作权限*/
	@Excel(name="页面操作权限",width=15)
	private String operation;
	/**数据权限*/
	@Excel(name="数据权限",width=15)
	private String datarule;
	/**创建人*/
	@Excel(name="创建人",width=15)
	private String createName;
	/**创建人id*/
	@Excel(name="创建人id",width=15)
	private String createBy;
	/**创建时间*/
	@Excel(name="创建时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**修改人*/
	@Excel(name="修改人",width=15)
	private String updateName;
	/**修改人id*/
	@Excel(name="修改人id",width=15)
	private String updateBy;
	/**修改时间*/
	@Excel(name="修改时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**所属部门*/
	@Excel(name="所属部门",width=15)
	private String sysOrgCode;
	/**所属公司*/
	@Excel(name="所属公司",width=15)
	private String sysCompanyCode;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ID
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ID
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  权限组ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	public TSFunctionGroupEntity getTsFunctionGroup() {
		return this.tsFunctionGroup;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  权限组ID
	 */
	public void setTsFunctionGroup(TSFunctionGroupEntity tsFunctionGroup) {
		this.tsFunctionGroup = tsFunctionGroup;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  权限ID
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_id")
	public TSFunction getTsFunction() {
		return this.tsFunction;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  权限ID
	 */
	public void setTsFunction(TSFunction tsFunction) {
		this.tsFunction = tsFunction;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  页面操作权限
	 */

	@Column(name ="OPERATION",nullable=true,length=2000)
	public String getOperation(){
		return this.operation;
	}


	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  页面操作权限
	 */
	public void setOperation(String operation){
		this.operation = operation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数据权限
	 */

	@Column(name ="DATARULE",nullable=true,length=1000)
	public String getDatarule(){
		return this.datarule;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数据权限
	 */
	public void setDatarule(String datarule){
		this.datarule = datarule;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人id
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人id
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */

	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人id
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人id
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */

	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
}
