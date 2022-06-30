package it.polito.tdp.provaLaurea.model;

public class CostVarianceByBuyerRow {
	
	private Buyer buyer;
	private double total;
	private Double repetitive;
	private Double notRepetitive;
	private Double averageCostVariance;
	private Double averagePercentageCostVariance;
	private Double lastCostVariance;
	private Double lastPercentageCostVariance;
	
	public CostVarianceByBuyerRow(Buyer buyer, double total, Double repetitive, Double notRepetitive,
			Double averageCostVariance, Double averagePercentageCostVariance, Double lastCostVariance,
			Double lastPercentageCostVariance) {
		super();
		this.buyer = buyer;
		this.total = total;
		this.repetitive = repetitive;
		this.notRepetitive = notRepetitive;
		this.averageCostVariance = averageCostVariance;
		this.averagePercentageCostVariance = averagePercentageCostVariance;
		this.lastCostVariance = lastCostVariance;
		this.lastPercentageCostVariance = lastPercentageCostVariance;
	}


	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Double getRepetitive() {
		return repetitive;
	}

	public void setRepetitive(Double repetitive) {
		this.repetitive = repetitive;
	}

	public Double getNotRepetitive() {
		return notRepetitive;
	}

	public void setNotRepetitive(Double notRepetitive) {
		this.notRepetitive = notRepetitive;
	}

	public Double getAverageCostVariance() {
		return averageCostVariance;
	}

	public void setAverageCostVariance(Double averageCostVariance) {
		this.averageCostVariance = averageCostVariance;
	}

	public Double getAveragePercentageCostVariance() {
		return averagePercentageCostVariance;
	}

	public void setAveragePercentageCostVariance(Double averagePercentageCostVariance) {
		this.averagePercentageCostVariance = averagePercentageCostVariance;
	}

	public Double getLastCostVariance() {
		return lastCostVariance;
	}

	public void setLastCostVariance(Double lastCostVariance) {
		this.lastCostVariance = lastCostVariance;
	}

	public Double getLastPercentageCostVariance() {
		return lastPercentageCostVariance;
	}

	public void setLastPercentageCostVariance(Double lastPercentageCostVariance) {
		this.lastPercentageCostVariance = lastPercentageCostVariance;
	}

}
