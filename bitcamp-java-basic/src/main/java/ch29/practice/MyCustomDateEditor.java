package ch29.practice;

import java.beans.PropertyEditorSupport;
import java.sql.Date;

public class MyCustomDateEditor extends PropertyEditorSupport{
  
  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    Date date = Date.valueOf(text);
    this.setValue(date);
  }
  
  @Override
  public Object getValue() {
    return super.getValue();
  }
}
