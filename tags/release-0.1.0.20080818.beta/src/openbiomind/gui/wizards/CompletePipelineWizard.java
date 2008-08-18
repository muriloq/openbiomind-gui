/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (CompletePipelineWizard.java) is part of OpenBiomind-GUI.
 *
 *  OpenBiomind-GUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenBiomind-GUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBiomind-GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Please visit the following pages to contact the author(s):
 *  Homepage: http://code.google.com/p/openbiomind-gui/
 *  Mailing list: http://groups.google.com/group/openbiomind-gui/
 */

package openbiomind.gui.wizards;

import java.io.File;

import openbiomind.gui.data.AbstractTaskData;
import openbiomind.gui.data.CompletePipelineTaskData;
import openbiomind.gui.main.GraphvizHelper;

/**
 * The class CompletePipelineWizard.
 * 
 * @author bsanghvi
 * @since Jul 31, 2008
 * @version Aug 18, 2008
 */
public class CompletePipelineWizard extends AbstractTaskWizard {

   /** The complete pipeline wizard page 1. */
   private final CompletePipelineWizardPage1 COMPLETE_PIPELINE_WIZ_PAGE_1 = new CompletePipelineWizardPage1();

   /** The complete pipeline wizard page 2. */
   private final CompletePipelineWizardPage2 COMPLETE_PIPELINE_WIZ_PAGE_2 = new CompletePipelineWizardPage2();

   /** The complete pipeline task data. */
   private CompletePipelineTaskData completePipelineTaskData = null;

   /** The Graphviz helper. */
   private GraphvizHelper graphvizHelper = null;

   /**
    * Instantiates a new complete pipeline wizard.
    */
   public CompletePipelineWizard() {
      super(Messages.CompPipeWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.COMPLETE_PIPELINE_WIZ_PAGE_1);
      addPage(this.COMPLETE_PIPELINE_WIZ_PAGE_2);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { getCompletePipelineTaskData() };
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.COMPLETE_PIPELINE_WIZ_PAGE_1;
   }

   /**
    * Gets the complete pipeline task data.
    * 
    * @return the complete pipeline task data
    */
   private CompletePipelineTaskData getCompletePipelineTaskData() {
      if (this.completePipelineTaskData == null) {
         final CompletePipelineTaskData page1TaskData = this.COMPLETE_PIPELINE_WIZ_PAGE_1.prepareTaskData();
         if (this.COMPLETE_PIPELINE_WIZ_PAGE_2.isCurrentPage()) {
            final CompletePipelineTaskData page2TaskData = this.COMPLETE_PIPELINE_WIZ_PAGE_2.prepareTaskData();
            page2TaskData.setInputDataset(page1TaskData.getInputDataset());
            page2TaskData.setOutputDirectory(page1TaskData.getOutputDirectory());
            page2TaskData.setTestDataset(page1TaskData.getTestDataset());
            page2TaskData.setPropertyFile(page1TaskData.getPropertyFile());
            this.completePipelineTaskData = page2TaskData;
         } else {
            this.completePipelineTaskData = page1TaskData;
         }
      }
      return this.completePipelineTaskData;
   }

   /**
    * Gets the graphviz helper.
    * 
    * @return the graphviz helper
    */
   private GraphvizHelper getGraphvizHelper() {
      if (this.graphvizHelper == null) {
         this.graphvizHelper = new GraphvizHelper(this.COMPLETE_PIPELINE_WIZ_PAGE_1.getOutputDirectory()
               + File.separator + Resources.GRAPH_DOT_FILENAME);
      }
      return this.graphvizHelper;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getPostSuccessfulExecutionProcess()
    */
   @Override
   protected Process getPostSuccessfulExecutionProcess() {
      final Process dotProcess = getGraphvizHelper().createDotProcess();
      if (dotProcess != null) {
         getCompletePipelineTaskData().setGraphImagePath(getGraphvizHelper().getOutputFilePath());
         return dotProcess;
      } else {
         return super.getPostSuccessfulExecutionProcess();
      }
   }

}
