package com.company.interview;

import org.junit.jupiter.api.Test;

import java.util.*;

public class EmailFolderLabel {
    public static class FolderObject {

        int id;
        int parentId;
        String name;

        public FolderObject(int id, int parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }
    }
    public List<String> getLabels(List<FolderObject> folders) {
        Map<Integer, FolderObject> map = new HashMap<>();
        for(FolderObject object: folders) {
            map.put(object.id, object);
        }

        List<String> result = new ArrayList<>();
        for(FolderObject object: folders) {
            result.add(getFullLabel(object, map));
        }

        return result;
    }

    private String getFullLabel(FolderObject object, Map<Integer, FolderObject> map) {
        List<String> labels = new LinkedList();
        while (object != null) {
            labels.add(0, object.name);
            if (object.parentId != 0) {
                object = map.get(object.parentId);
            } else {
                break;
            }
        }

        return String.join("/", labels );
    }

    @Test
    public void testRun() {
        /**
         *     {id: 27, parentId: 15, name: 'projects'},
         *     {id: 81, parentId: 27, name: 'novel'},
         *     {id: 15, parentId: 0, name: 'personal'}, // a parentId of 0 means root
         *     {id: 35, parentId: 27, name: 'blog'},
         */

        List<FolderObject> list = new ArrayList<>();
        list.add(new FolderObject(27, 15, "projects"));
        list.add(new FolderObject(81, 27, "novel"));
        list.add(new FolderObject(15, 0, "personal"));
        list.add(new FolderObject(35, 27, "blog"));

        EmailFolderLabel folderLabel = new EmailFolderLabel();
        List<String> result = folderLabel.getLabels(list);

        for(String s: result) {
            System.out.println(s);
        }
    }
}
