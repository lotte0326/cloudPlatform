package com.jeecg.person.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 入职员工
 * @author onlineGenerator
 * @date 2017-09-26 14:36:10
 * @version V1.0   
 *
 */
@Entity
@Table(name = "test_person", schema = "")
@SuppressWarnings("serial")
public class TestPersonEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**名字*/
	@Excel(name="名字",width=15)
	private java.lang.String name;
	/**性别*/
	@Excel(name="性别",width=15,dicCode="A01")
	private java.lang.String sex;
	/**生日*/
	@Excel(name="生日",width=15,format = "yyyy-MM-dd")
	private java.util.Date birthday;
	/**个人简介*/
	@Excel(name="个人简介",width=15)
	private java.lang.String conets;
	/**测性别*/
	@Excel(name="测性别",width=15,dicCode="sex")
	private java.lang.String cexs;
	/**工资*/
	@Excel(name="工资",width=15)
	private java.lang.Double salary;
	/**简历附件*/
	@Excel(name="简历附件",width=15)
	private java.lang.String fielJls;
	/**个人头像*/
	@Excel(name="个人头像",width=15)
	private java.lang.String touPic;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */

	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名字
	 */

	@Column(name ="NAME",nullable=false,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名字
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */

	@Column(name ="SEX",nullable=true,length=32)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生日
	 */

	@Column(name ="BIRTHDAY",nullable=true,length=32)
	public java.util.Date getBirthday(){
		return this.birthday;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生日
	 */
	public void setBirthday(java.util.Date birthday){
		this.birthday = birthday;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  个人简介
	 */

	@Column(name ="CONETS",nullable=true,length=32)
	public java.lang.String getConets(){
		return this.conets;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  个人简介
	 */
	public void setConets(java.lang.String conets){
		this.conets = conets;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测性别
	 */

	@Column(name ="CEXS",nullable=true,length=32)
	public java.lang.String getCexs(){
		return this.cexs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测性别
	 */
	public void setCexs(java.lang.String cexs){
		this.cexs = cexs;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  工资
	 */

	@Column(name ="SALARY",nullable=true,length=32)
	public java.lang.Double getSalary(){
		return this.salary;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  工资
	 */
	public void setSalary(java.lang.Double salary){
		this.salary = salary;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  简历附件
	 */

	@Column(name ="FIEL_JLS",nullable=true,length=1000)
	public java.lang.String getFielJls(){
		return this.fielJls;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  简历附件
	 */
	public void setFielJls(java.lang.String fielJls){
		this.fielJls = fielJls;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  个人头像
	 */

	@Column(name ="TOU_PIC",nullable=true,length=1000)
	public java.lang.String getTouPic(){
		return this.touPic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  个人头像
	 */
	public void setTouPic(java.lang.String touPic){
		this.touPic = touPic;
	}
}
