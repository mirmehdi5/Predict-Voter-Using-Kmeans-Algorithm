/*
 * JBoss+ Home of Professional Open Source
 * Copyright 2015+ Red Hat+ Inc. and/or its affiliates+ and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License+ Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing+ software
 * distributed under the License is distributed on an "AS IS" BASIS+
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND+ either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.greeter.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.as.quickstarts.greeter.domain.ModelDao;

/**
 * 
 * @author MIR
 * @class GeneralController
 * @brief This is the controller for prediction from the clusters
 *
 */
@Named
@RequestScoped
public class GeneralController {
	
	@Inject
    private ModelDao modelDao;

    private String input1;
    
    private String input2;
    private String input3;
    private String input4;
    private String input5;
    private String input6;
    private String input7;
    private String input8;
    private String input9;
    private String input10;
    private String input11;
    private String input12;
    private String input13;
    private String input14;
    private String input15;
    private String input16;
    
    private String model;

    private String greeting;

    /**
     * @brief This functions calls the predictModel function to predict which party does the particular voter support.
     */
    public void predictmodel() {
    	Double[] input_array = new Double[16];
    	input_array[0] = Double.parseDouble(getInput1());
    	input_array[1] = Double.parseDouble(getInput2());
    	input_array[2] = Double.parseDouble(getInput3());
    	input_array[3] = Double.parseDouble(getInput4());
    	input_array[4] = Double.parseDouble(getInput5());
    	input_array[5] = Double.parseDouble(getInput6());
    	input_array[6] = Double.parseDouble(getInput7());
    	input_array[7] = Double.parseDouble(getInput8());
    	input_array[8] = Double.parseDouble(getInput9());
    	input_array[9] = Double.parseDouble(getInput10());
    	input_array[10] = Double.parseDouble(getInput11());
    	input_array[11] = Double.parseDouble(getInput12());
    	input_array[12] = Double.parseDouble(getInput13());
    	input_array[13] = Double.parseDouble(getInput14());
    	input_array[14] = Double.parseDouble(getInput15());
    	input_array[15] = Double.parseDouble(getInput16());
    	
    	String result = modelDao.predictModel(input_array,getModel());
    	greeting = result;
    }

	/**
	 * @brief This function gets the rating for HANDICAPPED-INFANTS from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput1() {
        return input1;
    }

    /**
     * @brief This function sets the rating for HANDICAPPED-INFANTS to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput1(String input1) {
        this.input1 = input1;
    }
   
	/**
	 * @brief This function gets the rating for WATER-PROJECT-COST-SHARING from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput2() {
        return input2;
    }

    /**
     * @brief This function sets the rating for WATER-PROJECT-COST-SHARING to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput2(String input2) {
        this.input2 = input2;
    }
    
	/**
	 * @brief This function gets the rating for ADOPTION-OF-THE-BUDGET-RESOLUTION from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput3() {
        return input3;
    }

    /**
     * @brief This function sets the rating for ADOPTION-OF-THE-BUDGET-RESOLUTION to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput3(String input3) {
        this.input3 = input3;
    }
    
	/**
	 * @brief This function gets the rating for PHYSICIAN-FEE-FREEZE from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput4() {
        return input4;
    }

    /**
     * @brief This function sets the rating for PHYSICIAN-FEE-FREEZE to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput4(String input4) {
        this.input4 = input4;
    }
    
	/**
	 * @brief This function gets the rating for EL-SALVADOR-AID from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput5() {
        return input5;
    }

    /**
     * @brief This function sets the rating for EL-SALVADOR-AID to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput5(String input5) {
        this.input5 = input5;
    }
    
	/**
	 * @brief This function gets the rating for RELIGIOUS-GROUPS-IN-SCHOOLS from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput6() {
        return input6;
    }

    /**
     * @brief This function sets the rating for RELIGIOUS-GROUPS-IN-SCHOOLS to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput6(String input6) {
        this.input6 = input6;
    }
    
	/**
	 * @brief This function gets the rating for ANTI-SATELLITE-TEST-BAN from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput7() {
        return input7;
    }

    /**
     * @brief This function sets the rating for ANTI-SATELLITE-TEST-BAN to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput7(String input7) {
        this.input7 = input7;
    }
    
	/**
	 * @brief This function gets the rating for AID-TO-NICARAGUAN-CONTRAS from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput8() {
        return input8;
    }

    /**
     * @brief This function sets the rating for AID-TO-NICARAGUAN-CONTRAS to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput8(String input8) {
        this.input8 = input8;
    }
    
	/**
	 * @brief This function gets the rating for MX-MISSILE from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput9() {
        return input9;
    }

    /**
     * @brief This function sets the rating for MX-MISSILE to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput9(String input9) {
        this.input9 = input9;
    }
    
	/**
	 * @brief This function gets the rating for IMMIGRATION from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput10() {
        return input10;
    }

    /**
     * @brief This function sets the rating for IMMIGRATION to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput10(String input10) {
        this.input10 = input10;
    }
    
	/**
	 * @brief This function gets the rating for SYNFUELS-CORPORATION-CUTBACK from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput11() {
        return input11;
    }

    /**
     * @brief This function sets the rating for SYNFUELS-CORPORATION-CUTBACK to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput11(String input11) {
        this.input11 = input11;
    }
    
	/**
	 * @brief This function gets the rating for EDUCATION-SPENDING from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput12() {
        return input12;
    }

    /**
     * @brief This function sets the rating for EDUCATION-SPENDING to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput12(String input12) {
        this.input12 = input12;
    }
    
	/**
	 * @brief This function gets the rating for SUPERFUND-RIGHT-TO-SUE from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput13() {
        return input13;
    }

    /**
     * @brief This function sets the rating for SUPERFUND-RIGHT-TO-SUE to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput13(String input13) {
        this.input13 = input13;
    }
    
	/**
	 * @brief This function gets the rating for CRIME from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput14() {
        return input14;
    }

    /**
     * @brief This function sets the rating for CRIME to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput14(String input14) {
        this.input14 = input14;
    }
    
	/**
	 * @brief This function gets the rating for DUTY-FREE-EXPORTS from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput15() {
        return input15;
    }

    /**
     * @brief This function sets the rating for DUTY-FREE-EXPORTS to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput15(String input15) {
        this.input15 = input15;
    }
    
	/**
	 * @brief This function gets the rating for EXPORT-ADMINISTRATION-ACT-SOUTH-AFRICA from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getInput16() {
        return input16;
    }

    /**
     * @brief This function sets the rating for EXPORT-ADMINISTRATION-ACT-SOUTH-AFRICA to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setInput16(String input16) {
        this.input16 = input16;
    }
    
	/**
	 * @brief This function gets the class name in the database from the developer_page.xhtml.
	 * @return the grade out of 100 in string format.
	 */
    public String getModel() {
        return model;
    }

    /**
     * @brief This function sets the class name in the database to the developer_page.xhtml.
     * @param input1 rating out of 100in string
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
	 * @brief This function gets the greeting attribute from the general_page.XHTML page.
	 */
    public String getGreeting() {
        return greeting;
    }

}
