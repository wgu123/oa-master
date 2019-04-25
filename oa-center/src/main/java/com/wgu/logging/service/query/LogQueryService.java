package com.wgu.logging.service.query;


import com.wgu.entity.Logs;
import com.wgu.logging.repository.LogRepository;
import com.wgu.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author w
 * @date 2018-11-24
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogQueryService {

    private final LogRepository logRepository;

    @Autowired
    public LogQueryService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Object queryAll(Logs log, Pageable pageable){
        return PageUtils.resultWrap(logRepository.findAll(new Spec(log),pageable));
    }

    class Spec implements Specification<Logs> {

        private Logs log;

        public Spec(Logs log){
            this.log = log;
        }

        @Override
        public Predicate toPredicate(Root<Logs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();


            if(!ObjectUtils.isEmpty(log.getUsername())){
                list.add(cb.like(root.get("username").as(String.class),"%"+log.getUsername()+"%"));
            }

            if (!ObjectUtils.isEmpty(log.getLogType())) {
                list.add(cb.equal(root.get("logType").as(String.class), log.getLogType()));
            }

            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        }
    }
}
