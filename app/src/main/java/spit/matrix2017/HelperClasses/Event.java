package spit.matrix2017.HelperClasses;

/**
 * Created by Rohit on 13/11/16.
 */

public class Event {

    //////////Custom data type for events//////////
    private String name, description, category, venue, time, contact1_name, contact1_no, contact2_name, contact2_no;
        private int image,color;
        private long favourite, reminder;

        public String getVenue() {
            return venue;
        }

        public String getTime() {
            return time;
        }

        public String getContact1_name() {
            return contact1_name;
        }

        public String getContact2_name() {
            return contact2_name;
        }

        public int getImage() {
            return image;
        }

        public String getContact1_no() {
            return contact1_no;
        }

        public String getContact2_no() {
            return contact2_no;
        }

        public String getCategory() {
            return category;
        }

        public String getName() {
            return name;
        }

        public long getFavourite() {
            return favourite;
        }

        public long getReminder() {
            return reminder;
        }

        public String getDescription() {
            return description;
        }

    public int getColor() {
        return color;
    }


    public Event(String name, String description, int image, String category, String venue, String time, String contact1_name, String contact1_no, String contact2_name, String contact2_no, int color) {
            this.name = name;
            this.description = description;
            this.image = image;
            this.category = category;
            this.venue = venue;
            this.time = time;
            this.contact1_name = contact1_name;
            this.contact1_no = contact1_no;
            this.contact2_name = contact2_name;
            this.contact2_no = contact2_no;
            this.favourite=0;
            this.reminder=0;
            this.color=color;
        }

        public Event(String name, String description, int image, String category, String venue, String time, String contact1_name, String contact1_no, String contact2_name, String contact2_no, int favourite, int reminder,int color)
        {
            //used while fetching data
            this.name = name;
            this.description = description;
            this.image = image;
            this.category = category;
            this.venue = venue;
            this.time = time;
            this.contact1_name = contact1_name;
            this.contact1_no = contact1_no;
            this.contact2_name = contact2_name;
            this.contact2_no = contact2_no;
            this.favourite=favourite;
            this.reminder=reminder;
            this.color=color;
        }
    }


