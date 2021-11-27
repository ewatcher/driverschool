package com.tuocheng.model.demo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.aspectj.weaver.ast.HasAnnotation;

import com.alibaba.fastjson.JSON;

/**
 * 教练预约管理实体类(DAO层专用实体)
 * 
 * @author 农峰
 * 
 */
@Entity
@Table(name = "tb_trainerReservations", schema = "")
public class TtrainerReservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -142271400283143878L;
	private String id;// 标识
	private String schoolArea;// 校区标识
	private Integer fiveItem;// 五项总预约课时
	private Integer roadExam;// 路考试总预约课时
	private Integer studentTotal;// 预约学员总人数
	private String comment;// 备注

	private Ttrainer trainer;

	private Set<TtrainerReservationDetail> trainerReservationDetails = new HashSet<TtrainerReservationDetail>(
			0);

	public TtrainerReservation() {
	}

	public TtrainerReservation(String id, String schoolArea, Integer fiveItem,
			Integer roadExam, Integer studentTotal, String comment,
			Ttrainer trainer,
			Set<TtrainerReservationDetail> trainerReservationDetails) {
		this.id = id;
		this.schoolArea = schoolArea;
		this.fiveItem = fiveItem;
		this.roadExam = roadExam;
		this.studentTotal = studentTotal;
		this.comment = comment;
		this.trainer = trainer;
		this.trainerReservationDetails = trainerReservationDetails;
	}

	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "schoolArea", length = 36)
	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	@Column(name = "fiveItem", length = 10)
	public Integer getFiveItem() {
		return fiveItem;
	}

	public void setFiveItem(Integer fiveItem) {
		this.fiveItem = fiveItem;
	}

	@Column(name = "roadExam", length = 10)
	public Integer getRoadExam() {
		return roadExam;
	}

	public void setRoadExam(Integer roadExam) {
		this.roadExam = roadExam;
	}

	@Column(name = "comment", length = 300)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainerId")
	public Ttrainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Ttrainer trainer) {
		this.trainer = trainer;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "trainerReservation")
	public Set<TtrainerReservationDetail> getTrainerReservationDetails() {
		return trainerReservationDetails;
	}

	public void setTrainerReservationDetails(
			Set<TtrainerReservationDetail> trainerReservationDetails) {
		this.trainerReservationDetails = trainerReservationDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TtrainerReservation other = (TtrainerReservation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TtrainerReservation [id=" + id + ", schoolArea=" + schoolArea
				+ ", fiveItem=" + fiveItem + ", roadExam=" + roadExam
				+ ", studentTotal=" + studentTotal + ", comment=" + comment
				+ ", trainer=" + trainer + ", trainerReservationDetails="
				+ trainerReservationDetails + "]";
	}

}
