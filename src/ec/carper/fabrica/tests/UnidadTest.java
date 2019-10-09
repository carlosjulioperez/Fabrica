package ec.carper.fabrica.tests;

import org.openxava.tests.*;

public class UnidadTest extends ModuleTestBase { // Ha de extender de ModuleTestBase
    public UnidadTest (String testName) {
        // We indicate the application descripcion (Fabrica) and the module descripcion (Unidad)
        super(testName, "Fabrica", "Unidad"); // 
    }
 
    // The test methods must start with 'test'
    public void testCreateReadUpdateDelete() throws Exception {
        login("admin", "admin"); // The user sign in to access the module
 
        String descripcion = "JUNIT";
        String descripcion2 = "JUNI2";
        
        // Create
        execute("CRUD.new"); // Clicks on 'New' button
        setValue("descripcion", descripcion); // Sets the value for the 'descripcion' field to access a reference member
        execute("CRUD.save"); // Clicks on 'Save' button
        assertNoErrors(); // Verifies that the application does not show errors
        assertValue("descripcion", ""); // Verifies the 'number' field is empty
 
        // Read
        setValue("descripcion", descripcion); // Types 77 as the value for the 'number' field
        execute("CRUD.refresh"); // Clicks on 'Refresh' button
        assertValue("descripcion", descripcion); // and 'descripcion' has 'JUNIT Unidad'
 
        // Update
        setValue("descripcion", descripcion2); // Changes the value of 'descripcion' field
        execute("CRUD.save"); // Clicks on 'Save' button
        assertNoErrors(); // Verifies that the application does not show errors
        assertValue("descripcion", ""); // Verifies the 'descripcion' field is empty
 
        // Verify if modified
        setValue("descripcion", descripcion2); // Types 77 as the value for 'number' field
        execute("CRUD.refresh"); // Clicks on 'Refresh' button
        assertValue("descripcion", descripcion2); // and 'descripcion' has 'JUNIT Unidad MODIFIED'
 
        // Delete
        execute("CRUD.delete"); // Clicks on 'Delete' button
        assertMessage("Unidad deleted successfully"); // Verifies that the message 'Unidad deleted successfully' is shown to the user
    }
 
}

