package com.anatoliyadamitskiy.airball;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Anatoliy on 2/25/15.
 */
public class FileStorage {

    public static ArrayList<Shot> getShots(Context _context, String _category)
    {
        File external = _context.getExternalFilesDir(null);
        File file = new File(external, _category);
        ArrayList<Shot> shotArray = new ArrayList<>();

        try
        {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            shotArray = (ArrayList<Shot>) objectInputStream.readObject();
            fileInputStream.close();

            return shotArray;
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            return shotArray;
        }
    }

    public static Boolean saveShots(Context _context, String _category, ArrayList<Shot> _shotArray)
    {
        File external = _context.getExternalFilesDir(null);
        File file = new File(external, _category);

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(_shotArray);
            fileOutputStream.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Comment> getComments(Context _context)
    {
        File external = _context.getExternalFilesDir(null);
        File file = new File(external, "Comments");
        ArrayList<Comment> commentArray = new ArrayList<>();

        try
        {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            commentArray = (ArrayList<Comment>) objectInputStream.readObject();
            fileInputStream.close();

            return commentArray;
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            return commentArray;
        }
    }

    public static Boolean saveComments(Context _context, ArrayList<Comment> _commentArray)
    {
        File external = _context.getExternalFilesDir(null);
        File file = new File(external, "Comments");

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(_commentArray);
            fileOutputStream.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<User> getUser(Context _context)
    {
        File external = _context.getExternalFilesDir(null);
        File file = new File(external, "User");
        ArrayList<User> userArray = new ArrayList<>();

        try
        {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            userArray = (ArrayList<User>) objectInputStream.readObject();
            fileInputStream.close();

            return userArray;
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            return userArray;
        }
    }

    public static Boolean saveUser(Context _context, ArrayList<User> _userArray)
    {
        File external = _context.getExternalFilesDir(null);
        File file = new File(external, "User");

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(_userArray);
            fileOutputStream.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Shot> getFavorites(Context _context, String _category)
    {
        File external = _context.getExternalFilesDir(null);
        File file = new File(external, _category);
        ArrayList<Shot> shotArray = new ArrayList<>();

        try
        {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            shotArray = (ArrayList<Shot>) objectInputStream.readObject();
            fileInputStream.close();

            return shotArray;
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            return shotArray;
        }
    }

    public static Boolean saveFavorites(Context _context, String _category, Shot _shot)
    {
        File external = _context.getExternalFilesDir(null);
        File file = new File(external, _category);

        ArrayList<Shot> shotArray = new ArrayList<>();

        try
        {

            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                shotArray = (ArrayList<Shot>) objectInputStream.readObject();
                shotArray.add(_shot);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(shotArray);
                fileOutputStream.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            shotArray.add(_shot);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(shotArray);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;


     }

}
