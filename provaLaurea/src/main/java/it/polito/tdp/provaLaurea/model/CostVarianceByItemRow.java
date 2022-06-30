package it.polito.tdp.provaLaurea.model;

public class CostVarianceByItemRow {

	private Item item;
	private double total;
	private boolean repetitive;
	private boolean notRepetitive;
	private Double averageCostVariance;
	private Double averagePercentageCostVariance;
	private Double lastCostVariance;
	private Double lastPercentageCostVariance;
	
	public CostVarianceByItemRow(Item item, double total, boolean repetitive, boolean notRepetitive, Double averageCostVariance,
			Double averagePercentageCostVariance,Double lastCostVariance, Double lastPercentageCostVariance) {
		super();
		this.item = item;
		this.total = total;
		this.repetitive = repetitive;
		this.notRepetitive = notRepetitive;
		this.lastCostVariance = lastCostVariance;
		this.lastPercentageCostVariance = lastPercentageCostVariance;
		this.averageCostVariance = averageCostVariance;
		this.averagePercentageCostVariance = averagePercentageCostVariance;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isRepetitive() {
		return repetitive;
	}

	public void setRepetitive(boolean repetitive) {
		this.repetitive = repetitive;
	}

	public boolean isNotRepetitive() {
		return notRepetitive;
	}

	public void setNotRepetitive(boolean notRepetitive) {
		this.notRepetitive = notRepetitive;
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
	
}
