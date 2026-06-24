package it.eng.care.domain.flow.core.b2b.converter;

import it.eng.care.domain.flow.b2b.model.UserDTO;
import it.eng.care.platform.authentication.api.model.User;
import it.eng.care.platform.tool.transport.conversion.ConversionContext;
import it.eng.care.platform.tool.transport.conversion.Converter;

public class UserToUserDTO implements Converter<User, UserDTO> {



@Override
public void convert(User fromObject, UserDTO intoObject, ConversionContext ctx) {
	
	intoObject.setName(fromObject.getName());
	intoObject.setSurname(fromObject.getSurname());
	intoObject.setUsername(fromObject.getUsername());
	/*intoObject.setOrganization(fromObject.getOrganizations());
	intoObject.setSite(fromObject.getSites());
	intoObject.setRole(fromObject.getRoles());*/
	// TODO Auto-generated method stub
	
}

}
