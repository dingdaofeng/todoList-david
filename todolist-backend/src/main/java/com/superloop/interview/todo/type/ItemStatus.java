package com.superloop.interview.todo.type;

public enum ItemStatus {
	  Pending(1, "Pending"), Done(2, "Done");
	  private int value;
	  private String text;

	  private ItemStatus(int value, String text) {
	    this.value = value;
	    this.text = text;
	  }

	  public int toValue() {
	    return value;
	  }

	  public String toText() {
	    return text;
	  }

	  public static ItemStatus fromValue(int value) {
	    for (ItemStatus s : ItemStatus.values()) {
	      if (value == s.toValue())
	        return s;
	    }
	    return null;
	  }
}
