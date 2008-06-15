/**
 * EnhanceDatasetWizardPage1.java
 *
 * The file EnhanceDatasetWizardPage1.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.util.Constants;
import openbiomind.gui.util.Messages;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

/**
 * The class EnhanceDatasetWizardPage.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jun 13, 2008
 */
public class EnhanceDatasetWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /**
    * The constant for page name (value =
    * <code>openbiomind.gui.wizards.EnhanceDatasetWizardPage</code>).
    */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.EnhanceDatasetWizardPage"; //$NON-NLS-1$

   /** The base container. */
   private Composite baseContainer = null;

   /** The required group. */
   private Group requiredGroup = null;

   /** The optional group. */
   private Group optionalGroup = null;

   /** The original dataset text button composite. */
   private TextButtonComposite originalDatasetTextButtonComposite = null;

   /** The enhanced dataset text button composite. */
   private TextButtonComposite enhancedDatasetTextButtonComposite = null;

   /** The enhanced dataset text. */
   private Text enhancedDatasetText = null;

   /** The ontology description file text button composite. */
   private TextButtonComposite ontologyDescriptionFileTextButtonComposite = null;

   /** The ontology association file text button composite. */
   private TextButtonComposite ontologyAssociationFileTextButtonComposite = null;

   /**
    * Instantiates a new enhance dataset wizard page.
    *
    * @param pageTitle the page title
    * @param pageDescription the page description
    */
   public EnhanceDatasetWizardPage(final String pageTitle, final String pageDescription) {
      super(PAGE_NAME, pageTitle, pageDescription);
   }

   /*
    * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
    */
   @Override
   public void createControl(final Composite parent) {
      setParent(parent);

      /*
       * add components
       */
      getBaseContainer();

      // Required to avoid an error in the system
      setControl(getBaseContainer());

      setPageComplete(false);
   }

   /**
    * Gets the base container.
    *
    * @return the baseContainer
    */
   @Override
   protected Composite getBaseContainer() {
      if (this.baseContainer == null) {
         this.baseContainer = new Composite(getParent(), SWT.NULL);

         /*
          * apply layout information
          */
         GridLayoutFactory.swtDefaults().numColumns(1).applyTo(this.baseContainer);

         /*
          * add components
          */
         getRequiredGroup();
         getOptionalGroup();
      }

      return this.baseContainer;
   }

   /**
    * Gets the required group.
    *
    * @return the requiredGroup
    */
   private Group getRequiredGroup() {
      if (this.requiredGroup == null) {
         this.requiredGroup = new Group(getBaseContainer(), SWT.SHADOW_ETCHED_IN);
         this.requiredGroup.setText(Messages.RequiredArguments);

         /*
          * apply layout information
          */
         GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(
               this.requiredGroup);
         GridLayoutFactory.swtDefaults().numColumns(2).margins(10, 10).equalWidth(false).applyTo(
               this.requiredGroup);

         /*
          * add components
          */
         addInformationLabel(this.requiredGroup, Messages.Info_OriginalDataset);
         addComponentLabel(this.requiredGroup, Messages.Amp_SourceFile,
               Messages.Info_OriginalDataset);
         getOriginalDatasetTextButtonComposite();
         addSeparator(this.requiredGroup, SWT.HORIZONTAL);
         addInformationLabel(this.requiredGroup, Messages.Info_EnhancedDataset);
         addComponentLabel(this.requiredGroup, Messages.Amp_DestinationDirectory,
               Messages.Info_DestinationDirectory);
         getEnhancedDatasetTextButtonComposite();
         addComponentLabel(this.requiredGroup, Messages.Amp_DestinationFile,
               Messages.Info_EnhancedDataset);
         getEnhancedDatasetText();
      }

      return this.requiredGroup;
   }

   /**
    * Gets the original dataset text button composite.
    *
    * @return the originalDatasetTextButtonComposite
    */
   private TextButtonComposite getOriginalDatasetTextButtonComposite() {
      if (this.originalDatasetTextButtonComposite == null) {
         this.originalDatasetTextButtonComposite = new TextButtonComposite(getRequiredGroup()) {

            /** The file dialog. */
            private final FileDialog fileDialog = new FileDialog(getParent().getShell());

            @Override
            protected String buttonSelected() {
               return this.fileDialog.open();
            }

            @Override
            protected void textKeyReleased() {
            }

            @Override
            protected void onTextChange() {
            }

         };
      }

      return this.originalDatasetTextButtonComposite;
   }

   /**
    * Gets the enhanced dataset text button composite.
    *
    * @return the enhanced dataset text button composite
    */
   private TextButtonComposite getEnhancedDatasetTextButtonComposite() {
      if (this.enhancedDatasetTextButtonComposite == null) {
         this.enhancedDatasetTextButtonComposite = new TextButtonComposite(getRequiredGroup()) {

            /** The directory dialog. */
            private final DirectoryDialog directoryDialog = new DirectoryDialog(getParent()
                  .getShell());

            @Override
            protected String buttonSelected() {
               return this.directoryDialog.open();
            }

            @Override
            protected void textKeyReleased() {
            }

            @Override
            protected void onTextChange() {
            }

         };

         this.enhancedDatasetTextButtonComposite.getText().setText(Constants.CURRENT_DIRECTORY);
      }

      return this.originalDatasetTextButtonComposite;
   }

   /**
    * Gets the enhanced dataset text.
    *
    * @return the enhancedDatasetText
    */
   private Text getEnhancedDatasetText() {
      if (this.enhancedDatasetText == null) {
         this.enhancedDatasetText = new Text(getRequiredGroup(), SWT.SINGLE | SWT.BORDER);

         /*
          * apply layout information
          */
         GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).applyTo(
               this.enhancedDatasetText);

         /*
          * apply listeners
          */
         this.enhancedDatasetText.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(final KeyEvent e) {
            }

         });

         this.enhancedDatasetText.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(final FocusEvent e) {
            }

         });
      }

      return this.enhancedDatasetText;
   }

   /**
    * Gets the optional group.
    *
    * @return the optionalGroup
    */
   private Group getOptionalGroup() {
      if (this.optionalGroup == null) {
         this.optionalGroup = new Group(getBaseContainer(), SWT.SHADOW_ETCHED_IN);
         this.optionalGroup.setText(Messages.OptionalArguments);

         /*
          * apply layout information
          */
         GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(
               this.optionalGroup);
         GridLayoutFactory.swtDefaults().numColumns(2).margins(10, 10).equalWidth(false).applyTo(
               this.optionalGroup);

         /*
          * add components
          */
         addInformationLabel(this.optionalGroup, Messages.Info_OntologyDescription);
         addComponentLabel(this.optionalGroup, Messages.Amp_DescriptionFile,
               Messages.Info_OntologyDescription);
         getOntologyDescriptionFileTextButtonComposite();
         addSeparator(this.optionalGroup, SWT.HORIZONTAL);
         addInformationLabel(this.optionalGroup, Messages.Info_AssociationDescription);
         addComponentLabel(this.optionalGroup, Messages.Amp_AssociationFile,
               Messages.Info_AssociationDescription);
         getOntologyAssociationFileTextButtonComposite();
      }

      return this.optionalGroup;
   }

   /**
    * Gets the ontology description file text button composite.
    *
    * @return the ontologyDescriptionFileTextButtonComposite
    */
   private TextButtonComposite getOntologyDescriptionFileTextButtonComposite() {
      if (this.ontologyDescriptionFileTextButtonComposite == null) {
         this.ontologyDescriptionFileTextButtonComposite = new TextButtonComposite(
               getOptionalGroup()) {

            /** The file dialog. */
            private final FileDialog fileDialog = new FileDialog(getParent().getShell());

            @Override
            protected String buttonSelected() {
               return this.fileDialog.open();
            }

            @Override
            protected void textKeyReleased() {
            }

            @Override
            protected void onTextChange() {
            }

         };
      }

      return this.ontologyDescriptionFileTextButtonComposite;
   }

   /**
    * Gets the ontology association file text button composite.
    *
    * @return the ontologyAssociationFileTextButtonComposite
    */
   private TextButtonComposite getOntologyAssociationFileTextButtonComposite() {
      if (this.ontologyAssociationFileTextButtonComposite == null) {
         this.ontologyAssociationFileTextButtonComposite = new TextButtonComposite(
               getOptionalGroup()) {

            /** The file dialog. */
            private final FileDialog fileDialog = new FileDialog(getParent().getShell());

            @Override
            protected String buttonSelected() {
               return this.fileDialog.open();
            }

            @Override
            protected void textKeyReleased() {
            }

            @Override
            protected void onTextChange() {
            }

         };
      }

      return this.ontologyAssociationFileTextButtonComposite;
   }

}
