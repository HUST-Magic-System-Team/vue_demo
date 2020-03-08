package com.manli.manli_java.service;


import com.manli.manli_java.model_auto.TkiInstructionEntity;
import com.manli.manli_java.repository.TkiInstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TkiInstructionService {

    @Autowired
    private TkiInstructionRepository tkiInstructionRepository;

    public Page getList(int page, int size) {
        Sort sort = new Sort(Sort.Direction.ASC, "position");
        Pageable pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), sort);
        Page<TkiInstructionEntity> list = tkiInstructionRepository.findAllByStatus(Byte.valueOf("0"), pageable);

        return list;
    }
}
