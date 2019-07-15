package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lenovo
 * @Date: 2019-07-13 22:27
 * @Description:
 */
@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String labelId){
        return labelDao.findById(labelId).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    /**
     * 带参数模糊查询
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label) {
        List<Label> list = labelDao.findAll(getSpecification(label));
        return  list;
    }

    /**
     * 带参数分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    public Page<Label> findQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Label> all = labelDao.findAll(getSpecification(label), pageable);
        return all;
    }

    /**
     * 带参数查询的实体类封装
     * @param label
     * @return
     */
    private Specification<Label> getSpecification(Label label) {
        return new Specification<Label>() {
            /**
             *
             * @param root      需要查询的字段相当于 where 类名 = label.getId
             * @param query     类似于SQL中的group by / order by等关键字
             * @param cb        用来封装查询条件，如果直接返回null
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (!StringUtils.isEmpty(label.getLabelname())) {
                    Predicate labelname = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(labelname);
                }
                if (!StringUtils.isEmpty(label.getState())) {
                    Predicate state = cb.like(root.get("state").as(String.class), "%" + label.getState() + "%");
                    predicateList.add(state);
                }
                Predicate[] arrPredicate = new Predicate[predicateList.size()];
                predicateList.toArray(arrPredicate);
                return cb.and(arrPredicate);
            }
        };
    }
}
