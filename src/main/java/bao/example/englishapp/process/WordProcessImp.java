/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.process;

import bao.example.englishapp.model.Word;
import java.util.List;

/**
 *
 * @author Bao
 */
public class WordProcessImp implements IWordProcess{

    @Override
    public List<Word> selectAll() 
    {
        List<Word> listWord = null;
//        EntityManager manager = JPAUtil.getManager();
//        if(manager==null)
//        {
//            JPAUtil.createManager();
//            manager = JPAUtil.getManager();
//        }
//        EntityTransaction transaction = null;
//        
//        try {
//            transaction = manager.getTransaction();
//            transaction.begin();
//            TypedQuery<Word> query = manager.createQuery("FROM Word", Word.class);
//            listWord = query.getResultList();
//            transaction.commit();
//        } catch (Exception ex) {
//            if (transaction != null)
//                transaction.rollback();
////            System.out.println(ex.getMessage());
//        } finally {
//            manager.close();
//        }
        
        return listWord;
    }

    @Override
    public void add(Word word) {
//        EntityManager manager = JPAUtil.getManager();
//        if(manager==null)
//        {
//            JPAUtil.createManager();
//            manager = JPAUtil.getManager();
//        }
//        EntityTransaction transaction = null;
//
//        try {
//            transaction = manager.getTransaction();
//            transaction.begin();
//            manager.persist(word);
//            transaction.commit();
//        } catch (Exception ex) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            System.out.println(ex.getMessage());
//        } finally {
//            manager.close();
//        }
    }

    @Override
    public void update(Word word) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Word word) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
