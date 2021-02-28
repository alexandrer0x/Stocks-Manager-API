package dev.alexandrevieira.sm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PositionId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "broker_id")
	private Long brokerId;
	
	@Column(name = "stock_id")
	private Long stockId;
	
	public PositionId() {
		
	}

	public PositionId(Long userId, Long brokerId, Long stockId) {
		super();
		this.userId = userId;
		this.brokerId = brokerId;
		this.stockId = stockId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brokerId == null) ? 0 : brokerId.hashCode());
		result = prime * result + ((stockId == null) ? 0 : stockId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		PositionId other = (PositionId) obj;
		if (brokerId == null) {
			if (other.brokerId != null)
				return false;
		} else if (!brokerId.equals(other.brokerId))
			return false;
		if (stockId == null) {
			if (other.stockId != null)
				return false;
		} else if (!stockId.equals(other.stockId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PositionId [userId=" + userId + ", brokerId=" + brokerId + ", stockId=" + stockId + "]";
	}
	
	
}
