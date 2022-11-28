package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.exception.BadRequestionException;
import academy.devdojo.springboot2.mapper.AnimeMapper;
import academy.devdojo.springboot2.repository.AnimeRepository;
import academy.devdojo.springboot2.requests.AnimePostRequestBody;
import academy.devdojo.springboot2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animesRespository;

    public List<Anime> listAll() {
        return animesRespository.findAll();
    }
    public List<Anime> findByName(String name) {
        return animesRespository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return animesRespository.findById(id)
                .orElseThrow(() -> new BadRequestionException("Anime not found"));
    }

    @Transactional
    public Anime save(AnimePostRequestBody animeRepositoryRequestBody) {
        return animesRespository.save(AnimeMapper.INSTANCE.toAnime(animeRepositoryRequestBody));
    }

    public void delete(long id) {
        animesRespository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
      Anime savedAnime  = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
      Anime anime =  AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
      anime.setId(savedAnime.getId());
      animesRespository.save(anime);

    }
}
