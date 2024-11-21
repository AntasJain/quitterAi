package tech.antasjain.quitterAi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.entity.Addiction;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.repository.AddictionRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddictionService {

    private final AddictionRepository addictionRepository;

    public Addiction addAddiction(String name, String startDate, User user) {
        Addiction addiction = new Addiction();
        addiction.setName(name);
        addiction.setUser(user);

        if (startDate != null && !startDate.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            addiction.setStartDate(LocalDate.parse(startDate, formatter));
        }

        return addictionRepository.save(addiction);
    }

    public List<Addiction> getAddictionsByUser(User user) {
        return addictionRepository.findByUser(user);
    }

    public Optional<Addiction> getAddictionById(Long id) {
        return addictionRepository.findById(id);
    }

    public void deleteAddiction(Long addictionId) {
        addictionRepository.deleteById(addictionId);
    }
}
