package org.systemrendas;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition(info = @Info(description = "Aplicação para a gerencia de aluguel e venda de casas", title = "SysHouse", version = "0.0.1", contact = @Contact(name = "Luís Fernando Munhoz", email = "munhozfontana@gmail.com", url = "https://www.linkedin.com/in/luis-fernando-munhoz-fontana-neto-652aa88a/")))
public class App extends Application {

}