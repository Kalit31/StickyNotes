package com.example.stickynotes.model;

public class Item {
        String note;

        public Item(String note)
        {
            this.note = note;
        }

        public Item() {
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
}
