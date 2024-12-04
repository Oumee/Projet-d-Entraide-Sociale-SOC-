package soc.ms.msadmin.service;
import soc.ms.msadmin.service.dto.AdminDTO;
import java.util.List;
import java.util.Optional;

public interface AdminService {

    // Créer ou mettre à jour un admin à partir de AdminDTO
    AdminDTO saveAdmin(AdminDTO adminDTO);

    // Lire un admin par ID
    Optional<AdminDTO> getAdminById(String id);

    // Lire tous les admins
    List<AdminDTO> getAllAdmins();

    // Supprimer un admin par ID
    void deleteAdmin(String id);
}