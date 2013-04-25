package org.penguin.kayako;

import org.junit.Assert;
import org.junit.Test;
import org.penguin.kayako.domain.Department;
import org.penguin.kayako.domain.KayakoAccessibility;
import org.penguin.kayako.domain.KayakoApp;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class DepartmentTests {
    
    @Test
    public void testXmlParsingDepartment() throws Exception {
        // arrange
        String departmentsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_department.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Department.class);
        
        // act
        Department department = (Department) unmarshaller.unmarshal(new StringReader(departmentsXml));
        
        // assert
        Assert.assertNotNull(department);
        Assert.assertEquals(2, department.getId());
        Assert.assertEquals("Sales", department.getTitle());
        Assert.assertEquals(KayakoAccessibility.PUBLIC, department.getType());
        Assert.assertEquals("tickets", department.getModule());
        Assert.assertEquals(KayakoApp.TICKETS, department.getApp());
        Assert.assertEquals(30, department.getDisplayOrder());
        Assert.assertEquals(0, department.getParentDepartmentId());
        Assert.assertEquals(false, department.getUserVisibilityCustom());
    }
    
    @Test
    public void testXmlParsingDepartmentWithNoUserGroups() throws Exception {
        // arrange
        String departmentsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_department2.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Department.class);
        
        // act
        Department department = (Department) unmarshaller.unmarshal(new StringReader(departmentsXml));
        
        // assert
        Assert.assertEquals(0, department.getUserGroups().size());
    }
    
    @Test
    public void testXmlParsingDepartmentWithUserVisibilityCustom() throws Exception {
        // arrange
        String departmentsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_department2.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Department.class);
        
        // act
        Department department = (Department) unmarshaller.unmarshal(new StringReader(departmentsXml));
        
        // assert
        Assert.assertEquals(true, department.getUserVisibilityCustom());
    }
    
    @Test
    public void testXmlParsingDepartmentWithManyUserGroups() throws Exception {
        // arrange
        String departmentsXml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_department3.xml");
        
        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(Department.class);
        
        // act
        Department department = (Department) unmarshaller.unmarshal(new StringReader(departmentsXml));
        
        // assert
        Assert.assertEquals(3, department.getUserGroups().size());
        Assert.assertTrue(1234 == department.getUserGroups().get(1));
    }
}
