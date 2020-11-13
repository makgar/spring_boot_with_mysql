package lv.learning.spring_boot_with_mysql.service;

import lv.learning.spring_boot_with_mysql.model.entity.TitleEntity;
import lv.learning.spring_boot_with_mysql.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;

    public Page<TitleEntity> readTitles(Sort sort, Pageable pageRequest) {
        Pageable paging = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(), sort);

        Page<TitleEntity> titles = titleRepository.findAll(paging);
        return titles;
    }
}
