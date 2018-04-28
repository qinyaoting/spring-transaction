package test2.enum3;

public enum SortRule {
	
	DEFAULT("default"),
	PRICE_DESC("price_desc"),
	PRICE_ASC("price_asc"),
	COUNT_DESC("count_desc"),
	COUNT_ASC("count_asc"),
	TIME_DESC("time_desc"),
	TIME_ASC("time_asc");
	
	private String name;

	private SortRule(String name) {
		this.name = name;
	}
	
	private SortRule() {
	}
	
	public static SortRule getRule(String name) {
		for (SortRule val : SortRule.values()) {
			
			if (val.name.equals(name)) {
				return val;
			}
		}
		return SortRule.DEFAULT;
	}
	
	

}
