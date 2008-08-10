/**
 * UtilityComputerWizard.java
 *
 * The file UtilityComputerWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class UtilityComputerWizard.
 *
 * @author bsanghvi
 * @since Jul 9, 2008
 * @version Aug 10, 2008
 */
public class UtilityComputerWizard extends AbstractTaskWizard {

   /** The utility computer wizard page. */
   private final UtilityComputerWizardPage UTILITY_COMPUTER_WIZ_PAGE = new UtilityComputerWizardPage();

   /**
    * Instantiates a new utility computer wizard.
    */
   public UtilityComputerWizard() {
      super(Messages.UtilCompWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.UTILITY_COMPUTER_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { this.UTILITY_COMPUTER_WIZ_PAGE.prepareTaskData() };
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.UTILITY_COMPUTER_WIZ_PAGE;
   }

}
