package com.nowcoder.server;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class SensitiveService implements InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(SensitiveService.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        try{
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords.txt");
            InputStreamReader read = new InputStreamReader(is);
            BufferedReader bufferedReader= new BufferedReader(read);
            String lineTxt;
            while((lineTxt = bufferedReader.readLine()) != null) {
                addWord(lineTxt.trim());
            }
            read.close();
        } catch (Exception e) {
            logger.error("读取敏感词文件:"+e);
        } finally {

        }
    }

    private void addWord(String lineTxt) {
        TrieNode tmp = rootNode;
        for(int i=0;i< lineTxt.length();i++) {
            Character c = lineTxt.charAt(i);
            if(isSymbol(c)){
                continue;
            }
            TrieNode node = tmp.getSubNode(c);
            if(node == null) {
                node = new TrieNode();
                tmp.addSubNode(c,node);
            }

            if(i == lineTxt.length()-1) {
                node.setEnd(true);
            }
            tmp = node;
        }
    }

    private class TrieNode {
        private  boolean end = false;
        private Map<Character,TrieNode> subNodes = new HashMap<Character, TrieNode>();
        public void addSubNode(Character key,TrieNode node) {
            subNodes.put(key,node);
        }
        public TrieNode getSubNode(Character key) {
            return subNodes.get(key);
        }
        public boolean isEnd(){
            return end;
        }
        void setEnd(boolean e){
            end = e;
        }
    }
    private  TrieNode rootNode = new TrieNode();

    private boolean isSymbol(char c) {
        int ic = (int)c;
        return !CharUtils.isAsciiAlphanumeric(c) && (ic < 0x2E80 || ic > 0x9FFF);
    }

    public String filter(String text) {
        if(StringUtils.isBlank(text)) {
            return text;
        }
        StringBuilder result = new StringBuilder();
        String replacement = "***";
        TrieNode tempNode = rootNode;
        int begin = 0;
        int position =0;

        while(position < text.length()) {
            char c = text.charAt(position);


            if(isSymbol(c)) {
                if(tempNode == rootNode) {
                    result.append(c);
                    begin++;
                }
                position++;
                continue;
            }

            tempNode = tempNode.getSubNode(c);
            if(tempNode == null) {
                result.append(text.charAt(begin));
                position = begin+1;
                begin = position;
                tempNode = rootNode;
            } else if(tempNode.isEnd()) {
                result.append(replacement);
                position = position+1;
                begin = position;
                tempNode = rootNode;
            } else {
                position++;
            }


        }
        result.append(text.substring(begin));
        return result.toString();
    }

    public static void main(String[] args) {
        SensitiveService s = new SensitiveService();
        s.addWord("色情");
        s.addWord("赌博");
        String a = s.filter("赌 博哈哈色情哈哈赌博");
        System.out.println(a);
    }




















}
