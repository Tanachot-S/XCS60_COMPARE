package com.xcs.compare.model;


public class CompareMasLaw extends CompareMain{
	
	private int LawGroupID ;
	private String LawGroupNo ;
	private String LawGroupName ;
	private String PartNo ;
	private String PartName ;
	
	private CompareMasLawSection CompareMasLawSection ;
	private CompareMasLawSubSection CompareMasLawSubSection ;
	private CompareMasLawSubSectionRule CompareMasLawSubSectionRule ;
	private CompareMasLawGuiltBase CompareMasLawGuiltBase ;
	private CompareMasLawPenalty CompareMasLawPenalty ;
	public int getLawGroupID() {
		return LawGroupID;
	}
	public void setLawGroupID(int lawGroupID) {
		LawGroupID = lawGroupID;
	}
	public String getLawGroupNo() {
		return LawGroupNo;
	}
	public void setLawGroupNo(String lawGroupNo) {
		LawGroupNo = lawGroupNo;
	}
	public String getLawGroupName() {
		return LawGroupName;
	}
	public void setLawGroupName(String lawGroupName) {
		LawGroupName = lawGroupName;
	}
	public String getPartNo() {
		return PartNo;
	}
	public void setPartNo(String partNo) {
		PartNo = partNo;
	}
	public String getPartName() {
		return PartName;
	}
	public void setPartName(String partName) {
		PartName = partName;
	}
	public CompareMasLawSection getCompareMasLawSection() {
		return CompareMasLawSection;
	}
	public void setCompareMasLawSection(CompareMasLawSection compareMasLawSection) {
		CompareMasLawSection = compareMasLawSection;
	}
	public CompareMasLawSubSection getCompareMasLawSubSection() {
		return CompareMasLawSubSection;
	}
	public void setCompareMasLawSubSection(
			CompareMasLawSubSection compareMasLawSubSection) {
		CompareMasLawSubSection = compareMasLawSubSection;
	}
	public CompareMasLawSubSectionRule getCompareMasLawSubSectionRule() {
		return CompareMasLawSubSectionRule;
	}
	public void setCompareMasLawSubSectionRule(
			CompareMasLawSubSectionRule compareMasLawSubSectionRule) {
		CompareMasLawSubSectionRule = compareMasLawSubSectionRule;
	}
	public CompareMasLawGuiltBase getCompareMasLawGuiltBase() {
		return CompareMasLawGuiltBase;
	}
	public void setCompareMasLawGuiltBase(
			CompareMasLawGuiltBase compareMasLawGuiltBase) {
		CompareMasLawGuiltBase = compareMasLawGuiltBase;
	}
	public CompareMasLawPenalty getCompareMasLawPenalty() {
		return CompareMasLawPenalty;
	}
	public void setCompareMasLawPenalty(CompareMasLawPenalty compareMasLawPenalty) {
		CompareMasLawPenalty = compareMasLawPenalty;
	}
	
	
	

}
