package de.bht.fpa.mail.s786848.filter;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;

/**
 * This class represents a test case using three different filter cases
 */

final class Main {

  private Main() {
  }

  private static final int MESSAGE_NUMBER = 50;

  /**
   * @param args
   */
  public static void main(String[] args) {

    // Read Test
    IFilter read = new Read(false);

    // Union Test
    IFilter s = new Sender("arnold", FilterOperator.CONTAINS);
    IFilter r = new Recipients("@hotmail.de", FilterOperator.IS);
    IFilter union = new Union(s, r);

    // Intersection Test
    IFilter s2 = new Sender("trude", FilterOperator.STARTS_WITH);
    IFilter read2 = new Read(true);
    IFilter intersection = new Intersection(s2, read2);

    for (Message m : read.filter(new RandomTestDataProvider(MESSAGE_NUMBER).getMessages())) {
      System.out.println(m.isRead());
    }

    for (Message m : union.filter(new RandomTestDataProvider(MESSAGE_NUMBER).getMessages())) {
      System.out.println(m.toString());
    }

    for (Message m : intersection.filter(new RandomTestDataProvider(MESSAGE_NUMBER).getMessages())) {
      System.out.println(m.toString());
    }
  }
}
