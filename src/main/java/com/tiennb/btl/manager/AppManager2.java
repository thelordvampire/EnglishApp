package com.tiennb.btl.manager;

import com.tiennb.btl.model.ColectionWord;
import com.tiennb.btl.model.Question;
import com.tiennb.btl.model.Word;
import java.io.File;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AppManager2 {

    private ArrayList<ColectionWord> boTuVung;
    private List colectionWords;
    private ArrayList<Word> allWords, learnedWords;
    private ArrayList<Integer> fl;
    private int count;
    
    private int tuMoi;
    private int onTap;
    private int tongSoTuHomNayDaHoc;
    private int tongSoTuDaHoc;
    
    public AppManager2() throws IOException {
        initializeWords();
    }

    private void initializeWords() throws IOException {
        boTuVung = new ArrayList<>();
        allWords = new ArrayList<>();
    //    learnedWords = new ArrayList<Word>();
        fl = new ArrayList<>();
    //    count = 0;
        readFileFromExcel();
    }

    public void readFileFromExcel() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("Resource.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        ColectionWord colectionWord;
        int soSheet = workbook.getNumberOfSheets();

        for (int i = 0; i < soSheet; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            String nameSheet = workbook.getSheetName(i);
            FormulaEvaluator formula = workbook.getCreationHelper().createFormulaEvaluator();
            ArrayList<Word> words = new ArrayList<>();
            for (Row row : sheet) {
                Word word = new Word();
                if (row.getCell(0) != null) {
                //    System.out.println(row.getCell(0).toString());
                    word.setTa(row.getCell(0).toString());
                }
                if (row.getCell(1) != null) {
                    word.setCachDoc(row.getCell(1).toString());
                }
                if (row.getCell(2) != null) {
                    word.setTv(row.getCell(2).toString());
                }
                if (row.getCell(3) != null) {
                    word.setImagePath(row.getCell(3).toString());
                //    System.out.println(row.getCell(3).toString());
                }
                if (row.getCell(4) != null) {
                    word.setFlag((int) row.getCell(4).getNumericCellValue());
               //     System.out.println((int) row.getCell(4).getNumericCellValue());
                }
                words.add(word);
            }
            colectionWord = new ColectionWord(nameSheet, words);
            boTuVung.add(colectionWord);
        }
    }

    public void writeFileToExcel() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Resource.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        ColectionWord cl;
        ArrayList<Word> arr;
        for (int i =0; i < boTuVung.size();i++){
            XSSFSheet workSheet;
            cl = boTuVung.get(i);
            arr = cl.getWords();
            workSheet = workbook.createSheet(cl.getName());
            XSSFRow row;
            XSSFCell cellA, cellB, cellC, cellD, cellE;
            for (int j = 0; j< arr.size(); j++){
                row = workSheet.createRow(j);
                cellA = row.createCell(0);
                cellA.setCellValue(arr.get(j).getTa());
                cellB = row.createCell(1);
                cellB.setCellValue(arr.get(j).getCachDoc());
                cellC = row.createCell(2);
                cellC.setCellValue(arr.get(j).getTv());
                cellD = row.createCell(3);
                cellD.setCellValue(arr.get(j).getImagePath());
                cellE = row.createCell(4);
                cellE.setCellValue(arr.get(j).getFlag());
                
            }
        }

        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
    }

    public void addWordToColectionWord(Word word, String colName) throws IOException{
        /*System.out.println("OK");
        System.out.println(boTuVung.size());
        System.out.println(nameArrayList);*/
        for (int i = 0; i < boTuVung.size(); i++){
            //System.out.println(boTuVung.get(i).getName());
            if (boTuVung.get(i).getName().equals(colName)){
            //    System.out.println("OK");
                ColectionWord cl = boTuVung.get(i);
                ArrayList<Word> words = cl.getWords();
                words.add(word);
                writeFileToExcel();
                break;
            }
        }
    }

    public void deleteWord(String strTiengAnh, String nameArrayList) throws IOException{
        for (int i = 0; i < boTuVung.size(); i++){
            //System.out.println(boTuVung.get(i).getName());
            if (boTuVung.get(i).getName().equals(nameArrayList)){
                //    System.out.println("OK");
                ColectionWord cl = boTuVung.get(i);
                ArrayList<Word> words = cl.getWords();
                for (Word word : words) 
                    if(word.getTa().equals(strTiengAnh))
                    {
                        words.remove(word);
                        break;
                    }
//                words.remove(word);
                writeFileToExcel();
                break;
            }
        }
    }

    public void updateWord(String strTiengAnh, Word updateWord, String nameArrayList) throws IOException {
        for (int i = 0; i < boTuVung.size(); i++) {
            //System.out.println(boTuVung.get(i).getName());
            if (boTuVung.get(i).getName().equals(nameArrayList)) {
                //    System.out.println("OK");
                ColectionWord cl = boTuVung.get(i);
                ArrayList<Word> words = cl.getWords();
                for (Word word : words) 
                    if(word.getTa().equals(strTiengAnh))
                    {
//                        System.out.println("update word: "+updateWord.toString());
                        word.setTa(updateWord.getTa());
                        word.setTv(updateWord.getTv());
                        word.setCachDoc(updateWord.getCachDoc());
                        word.setImagePath(updateWord.getImagePath());
                        word.setFlag(updateWord.getFlag());
                        break;
                    }
                writeFileToExcel();
                break;
            }
        }
    }

    public void addColection(String nameColection) throws IOException{
        ColectionWord cl = new ColectionWord(nameColection, new ArrayList<>());
        boTuVung.add(cl);
        writeFileToExcel();
    }

    public void deleteColection(String nameColection) throws IOException{
        for (int i = 0; i < boTuVung.size(); i++){
            if (nameColection.equals(boTuVung.get(i).getName())){
                boTuVung.remove(boTuVung.get(i));
                break;
            }
        }
        writeFileToExcel();
    }
    
    public void gopHaiColection(String nameColectionA, String nameColectionB, 
           String nameNewColection) throws IOException {
        ColectionWord a = null, b=null;
        for (int i = 0; i < boTuVung.size(); i++){
            if (nameColectionA.equals(boTuVung.get(i).getName())){
                a = boTuVung.get(i);
                break;
            }
        }
       
        for (int i = 0; i < boTuVung.size(); i++){
            if (nameColectionB.equals(boTuVung.get(i).getName())){
                b = boTuVung.get(i);
                break;
            }
        }
       
        ArrayList<Word> wordsA = a.getWords();
        ArrayList<Word> wordsB = b.getWords();
        ArrayList<Word> wordsC = new ArrayList<>();
        for (int i = 0; i < wordsA.size(); i++)
           wordsC.add(wordsA.get(i));
       
        for (int i = 0; i < wordsB.size(); i++)
            wordsC.add(wordsB.get(i));
         
        ColectionWord c = new ColectionWord(nameNewColection, wordsC);
       
        boTuVung.remove(a);
        boTuVung.remove(b);
        boTuVung.add(c);
        writeFileToExcel();
    }

    public ArrayList getNameColection(){
        ArrayList names = new ArrayList<>();
        ColectionWord cl;
        for (int i = 0; i < boTuVung.size(); i++){
            cl = boTuVung.get(i);
            names.add(cl.getName());
        //    System.out.println(names[i]);
        }
        return names;
    }

    // danh sach tat ca cac tu
    public void getAllWord(){
        ArrayList<Word> a = new ArrayList<>();
        allWords = new ArrayList<>();
        for (int i = 0; i < boTuVung.size(); i++){
            a = boTuVung.get(i).getWords();
            for (int j = 0; j < a.size(); j++){
                allWords.add(a.get(j));
            }
        }
   //     System.out.println("allWords  " + allWords.size());
    //    System.out.println("OK");
    }
    
        public ArrayList<Word> getAllWordFromColection(String nameColection){
        for (int i =0; i < boTuVung.size(); i++){
            if (boTuVung.get(i).getName().equals(nameColection)){
                return  boTuVung.get(i).getWords();
            }
        }
        return null;
    }
    
    public ArrayList getNNewWord(int n){
        Random random = new Random();
        ArrayList<Word> nWords = new ArrayList<>();
        getAllWord();
        getListWordLearned();
    //    getMinFlag();
    
    
    if (fl.size() == 0){
            count = 0;
        } else{
        count = fl.get(fl.size()-1);}
    if ((allWords.size() - learnedWords.size()) < n){
        n = allWords.size() - learnedWords.size();
//        System.out.println("n : " + n);
       
        for (int t = 0 ; t < allWords.size();t++){
        if (allWords.get(t).getFlag() == 0){
            count++;
                nWords.add(allWords.get(t));
                allWords.get(t).setFlag(count);
            }
        }
    //    return nWords;
    } else{
        
        int all = allWords.size();
    //    System.out.println(all);
        int i = 0;
        int t;
        while (i <= n) {            
            t = random.nextInt(all-1);
//            System.out.println("i " + i);
//            System.out.println("n " + n);
            if (allWords.get(t).getFlag() == 0){
                count++;
                nWords.add(allWords.get(t));
                allWords.get(t).setFlag(count);
                
//                System.out.println(count);
//                System.out.println(allWords.get(t).getTa());
                i++;
            }
        }
    }   
        return nWords;
    }
    
    private void getMinFlag(){
        getListWordLearned();
        fl = new ArrayList<>();
        int f;
        for (int i = 0; i < learnedWords.size(); i++){
            f = learnedWords.get(i).getFlag();
            fl.add(f);
        }
        
        Collections.sort(fl);
        
    //    System.out.println("fl  " + fl.get(0));
        
    //    return fl.get(0);
    }
    
    public ArrayList getMLastestOldWordAndNNewWord(){
         getAllWord();
        // int minFl = getMinFlag();
        // TODO
    //    ArrayList<Word> nAndmWord = new ArrayList<Word>();
        ArrayList<Word> nWord = getNNewWord(5);
        ArrayList<Word> mWord = getMLastestOldWord(5);
     //   System.out.println(mWord.get(3).getTa());
        
        for (int i = 0; i < mWord.size();i++){
            nWord.add(mWord.get(i));
//            System.out.println(mWord.get(i).getTa());
        }
//        System.out.println(nWord.size());
        return nWord;
    }
    public ArrayList getMLastestOldWord(int m){
        getAllWord();
    //    System.out.println("OK");
    //    System.out.println("1");
        getListWordLearned();
    //    System.out.println("2");
        getMinFlag();
    //    System.out.println("3");
//        System.out.println("size : " + learnedWords.size() );
        Random random = new Random();
        ArrayList<Word> mWords = new ArrayList<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < learnedWords.size();j++){
                if (fl.get(i) == learnedWords.get(j).getFlag()){
                    mWords.add(learnedWords.get(j));
                    
                    break;
                }
            }
        }
//        System.out.println("mWord : " + mWords.size());
        return mWords;
    }
    
    
    
    public void getListWordLearned(){
        learnedWords = new ArrayList<>();
        getAllWord();
//        System.out.println("trc : "+ learnedWords.size());
//        System.out.println("size : " + allWords.size());
        for (int i = 0; i < allWords.size(); i++){
            if (allWords.get(i).getFlag() != 0){
             //   System.out.println("i : " + i);
             //   System.out.println("Tu : " + allWords.get(i).getTa());
             //   System.out.println("flag = " + allWords.get(i).getFlag());
                learnedWords.add(allWords.get(i));
            }
        }
//        System.out.println("sau : " + learnedWords.size());
    }
    
    public boolean searchWordFromListWordLearned(String wordEng){
		getAllWord();
        getListWordLearned();
        for (int i = 0; i < learnedWords.size(); i++){
            if ((learnedWords.get(i).getTa()).equals(wordEng)){
//                System.out.println("yes");
                return true;
            }
        }
        return true;
    }
    
    
     public Question makeAQuestionXuoi(int pos){
        getListWordLearned();
        
        int size = learnedWords.size();
        
        Question qs= new Question();
        
        qs.setQuestion(learnedWords.get(pos).getTa());
        qs.setResult(learnedWords.get(pos).getTv());
        
        String dapanA = null;
        String dapanB = null;
        String dapanC = null;
        int i,j,k;
        for (i = 0; i<allWords.size(); i++){
            if (!allWords.get(i).getTa().equals(qs.getQuestion())){
                dapanA = allWords.get(i).getTv();
                break;
            }
        }
        for (j = i+1; j<allWords.size(); j++){
            if (!allWords.get(j).getTa().equals(qs.getQuestion())){
                dapanB = allWords.get(j).getTv();
                break;
            }
        }
        for (k = j+1; k<allWords.size(); k++){
            if (!allWords.get(i).getTa().equals(qs.getQuestion())){
                dapanC = allWords.get(k).getTv();
                break;
            }
        }
        ArrayList<String> s = new ArrayList<>();
        s.add(dapanC);
        s.add(dapanA);
        s.add(dapanB);
        s.add(learnedWords.get(pos).getTv());
        
         Collections.shuffle(s);
         
       qs.setDapanA(s.get(0));
       qs.setDapanB(s.get(1));
       qs.setDapanC(s.get(2));
       qs.setDapanD(s.get(3));
       
//         System.out.println("Dap an "+ qs.getResult());
//         System.out.println(qs.getDapanA());
//         System.out.println(qs.getDapanB());
//         System.out.println(qs.getDapanC());
//         System.out.println(qs.getDapanD());
         
       return qs;
   }
     
     public Question makeAQuestionNguoc(int pos){
        getListWordLearned();
        
        int size = learnedWords.size();
        
        Question qs= new Question();
        
        qs.setQuestion(learnedWords.get(pos).getTv());
        qs.setResult(learnedWords.get(pos).getTa());
        
        String dapanA = null;
        String dapanB = null;
        String dapanC = null;
        int i,j,k;
        for (i = 0; i<allWords.size(); i++){
            if (!allWords.get(i).getTv().equals(qs.getQuestion())){
                dapanA = allWords.get(i).getTa();
                break;
            }
        }
        for (j = i+1; j<allWords.size(); j++){
            if (!allWords.get(j).getTv().equals(qs.getQuestion())){
                dapanB = allWords.get(j).getTa();
                break;
            }
        }
        for (k = j+1; k<allWords.size(); k++){
            if (!allWords.get(i).getTv().equals(qs.getQuestion())){
                dapanC = allWords.get(k).getTa();
                break;
            }
        }
        ArrayList<String> s = new ArrayList<>();
        s.add(dapanC);
        s.add(dapanA);
        s.add(dapanB);
        s.add(learnedWords.get(pos).getTa());
        
         Collections.shuffle(s);
         
       qs.setDapanA(s.get(0));
       qs.setDapanB(s.get(1));
       qs.setDapanC(s.get(2));
       qs.setDapanD(s.get(3));
       
//         System.out.println("cau hoi " + qs.getQuestion());
//         System.out.println("Dap an "+ qs.getResult());
//         System.out.println(qs.getDapanA());
//         System.out.println(qs.getDapanB());
//         System.out.println(qs.getDapanC());
//         System.out.println(qs.getDapanD());
         
       return qs;
   }
    
     public ArrayList makeListQuestion(){
    //     System.out.println(learnedWords.size());
   
        getListWordLearned();
//        System.out.println(learnedWords.size());
         ArrayList<Question> q = new ArrayList();
         for (int i = 0; i < learnedWords.size();i++){
             q.add(makeAQuestionXuoi(i));
             q.add(makeAQuestionNguoc(i));
         }
         Collections.shuffle(q);
//         System.out.println(q.size());
         return q;
     }
     
    public boolean checkQuestion(String ketQua, String dapAnDuocChon){
        return ketQua.equals(dapAnDuocChon);
    }
   
    public void saveFile(File imageFile) throws IOException
    {
        if(imageFile!=null)
        {
            File file= new File("src/main/resources/images/"+imageFile.getName());
            if(!file.exists())
            {
                byte[] bytesArray = new byte[(int) imageFile.length()];
                try (FileInputStream fis = new FileInputStream(imageFile)) {
                    fis.read(bytesArray);
                }
                Files.write(Paths.get(file.toURI()), bytesArray);
            }
        }
    }
    
    public int soTuMoiHomNayHoc(){
        return tuMoi;
    }
    
    public int soTuDaOnTap(){
        return onTap;
    }
    
    public int soTuHomNayDaHoc(){
        return tuMoi + onTap;
    }
    
    public int soTuDaHoc(){
        return tongSoTuDaHoc;
    }

}