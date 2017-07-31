package com.company;

//Pavlo Khryshcheniuk gr7
//package source1;

import java.util.Scanner;

class Train {

    String name;
    Train next;
    Wagon first;
    Wagon last;

    public Train(String name, String wname) {

        this.name = name;
        this.next = null;
        Wagon wagon = new Wagon(wname);
        first = wagon;
        last = wagon;
    }
}

class Wagon {

    String name;
    Wagon next;
    Wagon prev;

    public Wagon(String name) {
        this.name = name;
    }
}

class TrainLink {

    Train first;

    public Train AddTrain(Train train) {
        train.next = first;
        first = train;
        return train;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public TrainLink() {
        this.first = null;
    }

    public void CleanList() {
        this.first = null;
    }

    public Train DeleteTrain(Train train) {
        String todel;
        todel = train.name;
        Train temp;
        temp = first;
        Train previous = null;

        while (temp != null) {
            if (temp.name.equals(todel)) {
                if (temp == first) {
                    first = first.next;
                } else {
                    previous.next = temp.next;
                }
            } else {
                previous = temp;
            }
            temp = temp.next;
        }

        return train;
    }

    public void DisplayList() {

        System.out.print("Trains:");
        Train current = first;
        while (current != null) {

            System.out.print(" " + current.name);
            current = current.next;

        }
        System.out.println();
    }

    public Train Search(String train) {
        Train current;
        current = first;

        while (current != null) {
            if (current.name.equals(train)) {
                break;
            } else {
                current = current.next;
            }
        }

        return current;
    }

    public Train DeleteFirst(String train, String name) {
        Train temp;
        temp = first;
        if (!(temp.name.equals(train)) || temp == null) {
            temp = Search(train);
        }
        boolean singlewagon = false;
        Wagon first = temp.first;

        if (temp.first == temp.last) {
            DeleteTrain(temp);
            singlewagon = true;
        }

        Train t1 = new Train(name, first.name);

        AddTrain(t1);

        if (singlewagon == false) {
            if (temp.first.next != null) {
                if (temp.first.next.prev == temp.first) {
                    temp.first = temp.first.next;
                    temp.first.prev = null;
                } else {
                    temp.first = temp.first.next;
                    temp.first.next = null;
                }

            } else {
                if (temp.first.prev.next == temp.first) {
                    temp.first = temp.first.prev;
                    temp.first.next = null;
                } else {
                    temp.first = temp.first.prev;
                    temp.first.prev = null;
                }
            }
        }
        return temp;
    }

    public Train DisplayTrain(String train) {
        Train temp = first;
        if (temp == null || !temp.name.equals(train)) {
            temp = Search(train);
        }
        boolean direction = true;

        Wagon current = temp.first;

        System.out.print(temp.name + ":");

        if (temp.last == temp.first) {
            System.out.println(" " + temp.first.name);
        } else {

            while (current != temp.last) {
                System.out.print(" " + current.name);

                if (current.prev == null) {
                    if (current.next.prev == current) {
                        direction = true;
                    } else {
                        direction = false;
                    }
                    current = current.next;

                } else if (current.next == null) {
                    if (current.prev.next == current) {
                        direction = false;
                    } else {
                        direction = true;
                    }
                    current = current.prev;

                } else if (direction) {
                    if (current.next.prev == current) {
                        direction = true;
                    } else {
                        direction = false;
                    }
                    current = current.next;

                } else {

                    if (current.prev.next == current) {
                        direction = false;
                    } else {
                        direction = true;
                    }
                    current = current.prev;

                }
            }
            System.out.println(" " + temp.last.name);
        }
        return temp;
    }

    public Train DeleteLast(String train, String name) {
        Train temp;
        temp = first;
        if (!(temp.name.equals(train)) || temp == null) {
            temp = Search(train);
        }
        boolean singlewagon = false;
        Wagon last;
        last = temp.last;

        if (temp.first == temp.last) {
            DeleteTrain(temp);
            singlewagon = true;
        }

        Train t1 = new Train(name, last.name);
        AddTrain(t1);

        if (singlewagon == false) {
            if (temp.last.prev != null) {
                if (temp.last.prev.next == temp.last) {
                    temp.last = temp.last.prev;
                    temp.last.next = null;
                } else {
                    temp.last = temp.last.prev;
                    temp.last.prev = null;
                }

            } else {
                if (temp.last.next.prev == temp.last) {
                    temp.last = temp.last.next;
                    temp.last.prev = null;
                } else {
                    temp.last = temp.last.next;
                    temp.last.next = null;
                }
            }
        }
        return temp;
    }

    public Train InsertFirstWagon(String train, String name) {
        Train temp;
        temp = first;
        if (!temp.name.equals(train) || temp == null) {
            temp = Search(train);
        }

        Wagon w1 = new Wagon(name);

        if (temp.first.prev != null && temp.first.next == null) {
            Wagon w2 = temp.first;
            temp.first = w1;
            w2.next = w1;
            w1.prev = w2;

        } else {

            w1.next = temp.first;
            temp.first.prev = w1;
            temp.first = w1;
        }
        return temp;
    }

    public Train InsertLastWagon(String train, String name) {
        Train temp;
        temp = first;
        if (!(temp.name.equals(train)) || temp == null) {
            temp = Search(train);
        }

        Wagon w1 = new Wagon(name);

        if (temp.last.next != null && temp.last.prev == null) {
            Wagon w2 = temp.last;
            temp.last = w1;
            w1.next = w2;
            w2.prev = w1;

        } else {
            Wagon w2 = temp.last;
            temp.last = w1;
            w1.prev = w2;
            w2.next = w1;
        }
        return temp;
    }

    public Train Union(String train1, String train2) {
        Train temp1;
        temp1 = Search(train1);
        Train temp2;
        temp2 = Search(train2);

        if (temp1.last.next == null && temp1.last.prev != null) {
            if (temp2.last.prev == null && temp2.last.next != null) {
                temp1.last.next = temp2.first;
                temp2.first.next = temp1.last;
                temp1.last = temp2.last;
            } else {
                temp1.last.next = temp2.first;
                temp2.first.prev = temp1.last;
                temp1.last = temp2.last;
            }

        } else {
            if (temp2.first.next == null && temp2.first.prev != null) {
                temp1.last.prev = temp2.first;
                temp2.first.next = temp1.last;
                temp1.last = temp2.last;
            } else {
                temp1.last.prev = temp2.first;
                temp2.first.prev = temp1.last;
                temp1.last = temp2.last;
            }
        }
        DeleteTrain(temp2);
        return temp1;
    }

    public void Reverse(String train) {

        Train temp;
        temp = first;
        if (!temp.name.equals(train) || temp == null) {
            temp = Search(train);
        }

        if (temp.first != temp.last) {

            Wagon tempw = temp.first;
            temp.first = temp.last;
            temp.last = tempw;

        }
    }
}

public class Main {

    private static Scanner inScan = new Scanner(System.in);

    public static void main(String[] args) {

        int reps;
        reps = inScan.nextInt();

        for (int i = 0; i < reps; i++) {

            TrainLink TrainLinkList = new TrainLink();

            int instruct = inScan.nextInt();

            for (int j = 0; j < instruct; j++) {

                String command;

                command = inScan.next();

                if ("New".equals(command)) {
                    String t1_name;
                    t1_name = inScan.next();
                    String wagon;
                    wagon = inScan.next();
                    Train train = new Train(t1_name, wagon);
                    TrainLinkList.AddTrain(train);

                } else if ("InsertFirst".equals(command)) {
                    String t1_name;
                    t1_name = inScan.next();
                    String wagon;
                    wagon = inScan.next();
                    TrainLinkList.InsertFirstWagon(t1_name, wagon);

                } else if ("InsertLast".equals(command)) {
                    String t1_name;
                    t1_name = inScan.next();
                    String wagon;
                    wagon = inScan.next();
                    TrainLinkList.InsertLastWagon(t1_name, wagon);

                } else if ("Display".equals(command)) {
                    String t1_name;
                    t1_name = inScan.next();
                    TrainLinkList.DisplayTrain(t1_name);

                } else if ("TrainsList".equals(command)) {
                    TrainLinkList.DisplayList();

                } else if ("Reverse".equals(command)) {
                    String train = inScan.next();
                    TrainLinkList.Reverse(train);

                } else if ("Union".equals(command)) {
                    String t1_name;
                    t1_name = inScan.next();
                    String t2_name;
                    t2_name = inScan.next();
                    TrainLinkList.Union(t1_name, t2_name);

                } else if ("DelFirst".equals(command)) {
                    String t1_name;
                    t1_name = inScan.next();
                    String t2_name;
                    t2_name = inScan.next();
                    TrainLinkList.DeleteFirst(t1_name, t2_name);

                } else if ("DelLast".equals(command)) {
                    String t1_name;
                    t1_name = inScan.next();
                    String t2_name;
                    t2_name = inScan.next();
                    TrainLinkList.DeleteLast(t1_name, t2_name);

                }
            }
            TrainLinkList.CleanList();

        }
        inScan.close();
    }
}
