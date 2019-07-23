package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @auther: tangkc
 * @Date: 2019/7/23 21:55
 * @Description:
 */
@Service
@Transactional//开启事务，用于对数据库有修改的操作
public class SpitService {

	@Autowired
	private SpitDao spitDao;

	@Autowired
	private IdWorker idWorker;

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 *
	 * 查询所有
	 * @return
	 */
	public List<Spit> findAll(){
		return spitDao.findAll();
	}

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public Spit findById(String id){
		return  spitDao.findById(id).get();
	}

	/**
	 * 保存
	 * @param spit
	 */
	public void save(Spit spit){
		spit.set_id(idWorker.nextId()+"");
		spit.setPublishtime(new Date());
		spit.setVisits(0);
		spit.setShare(0);
		spit.setThumbup(0);
		spit.setComment(0);
		spit.setState("1");

		// 父节点更新
		if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
			Update update = new Update();
			update.inc("comment", 1);
			mongoTemplate.updateFirst(query, update, "spit");
		}

		spitDao.save(spit);
	}

	/**
	 * 根据主键删除
	 * @param id
	 */
	public void deleteById(String id){
		spitDao.deleteById(id);
	}

	/**
	 * 根据根节点查询
	 * @param id
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Spit> findByParentid(String id, int page, int size){
		Pageable pageable = PageRequest.of(page-1, size);
		return spitDao.findByParentid(id,pageable);
	}

	public void thumbup(String spitId) {
		// 方式一，效率低，先查，修改后再存
//        Spit spit = spitDao.findById(spitId).get();
//        spit.setThumbup((spit.getThumbup() == null ? 0 : spit.getThumbup()) + 1);
//        spitDao.save(spit);

		// 方式二：使用原生mongo命令来实现自增，只有一次数据库交互操作
		// db.spit.update({"_id": "1"}, {$inc: {thumbup:NumberInt(1)}})
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(1));
		Update update = new Update();
		update.inc("thumbup", 1);
		mongoTemplate.updateFirst(query, update, "spit");
	}
}
