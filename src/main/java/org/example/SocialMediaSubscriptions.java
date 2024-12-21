package org.example;

import java.time.LocalDateTime;

public class SocialMediaSubscriptions implements Comparable{
    private final String User;
    private final Channel channel;
    private LocalDateTime dateSubscription;

    public SocialMediaSubscriptions(String user, Channel channel) {
        User = user;
        this.channel = channel;
    }

    public SocialMediaSubscriptions(String user, Channel channel, LocalDateTime dateSubscription) {
        User = user;
        this.channel = channel;
        this.dateSubscription = dateSubscription;
    }

    public String getUser() {
        return User;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setDateSubscription(LocalDateTime dateSubscription) {
        this.dateSubscription = dateSubscription;
    }

    public LocalDateTime getDateSubscription() {
        return dateSubscription;
    }

    @Override
    public String toString() {
        return "SocialMediaSubscriptions{" +
                "User='" + User + '\'' +
                ", channel=" + channel +
                ", dateSubscription=" + dateSubscription +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        if (o instanceof SocialMediaSubscriptions) {
            return Integer.compare(channel.getOrder(), ((SocialMediaSubscriptions) o).channel.getOrder());
        }
        return 0;
    }
}
