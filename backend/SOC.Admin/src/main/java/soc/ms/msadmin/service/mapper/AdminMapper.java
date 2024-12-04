package soc.ms.msadmin.service.mapper;

import soc.ms.msadmin.service.dto.AdminDTO;
import soc.ms.msdatamongodb.data.SocAdmin;

public abstract class AdminMapper {

    // Conversion de AdminDTO vers Admin
    // Ignorer l'ID lors de la conversion
    public static SocAdmin toEntity(AdminDTO adminDTO) {
        return SocAdmin.builder()
                .id(adminDTO.id())
                .name(adminDTO.name())
                .email(adminDTO.email())
                .build();
    }

    // Conversion de Admin vers AdminDTO
    public static AdminDTO toDTO(SocAdmin admin) {
        return new AdminDTO(
                admin.getId(),
                admin.getName(),
                admin.getEmail()
        );
    }
}
