package soc.ms.msadmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soc.ms.msadmin.service.AdminService;
import soc.ms.msadmin.service.dto.AdminDTO;
import soc.ms.msadmin.service.mapper.AdminMapper;
import soc.ms.msdatamongodb.data.SocAdmin;
import soc.ms.msdatamongodb.repository.ISocAdminRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ISocAdminRepository adminRepository;

    @Override
    public AdminDTO saveAdmin(AdminDTO adminDTO) {
        var admin = AdminMapper.toEntity(adminDTO);
        admin.setId(null); // Set to null to allow MongoDB to auto-generate the ID
        var savedAdmin = adminRepository.save(admin);
        return AdminMapper.toDTO(savedAdmin);
    }

    @Override
    public Optional<AdminDTO> getAdminById(String id) { // Utilisation de Long
        return adminRepository.findById(id)
                .map(AdminMapper::toDTO); // Mapper vers DTO
    }

    @Override
    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(AdminMapper::toDTO) // Mapper vers DTO
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdmin(String id) { // Utilisation de Long
        adminRepository.deleteById(id);
    }
}
