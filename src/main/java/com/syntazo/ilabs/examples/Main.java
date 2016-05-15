package com.syntazo.ilabs.examples;

import com.syntazo.ilabs.adapters.BufferReader;
import com.syntazo.ilabs.core.*;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: 4/7/11
 * Time: 1:55 PM
 */
public class Main {
    public static void main(String[] args) {
       final Buffer OM_QUEUE = new Buffer(new QueuedBufferStrategy<Envelope>());
        // set up producer
        Timer producer = new Timer();
        producer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < 12; i++) {
                    Message attributeList = new Message();
                    attributeList.put(Attribute.DESCRIPTION, "Data Agent " + i);
                    Event event;
                    String id;
                    switch (i % 4) {
                        case 0:
                            event = new BO5(this);
                            id = "BO5";
                            break;
                        case 1:
                            event = new BO36(this);
                            id = "BO36";
                            break;
                        case 2:
                            event = new BD6(this);
                            id = "BD6";
                            break;
                        default:
                            event = new BO14(this);
                            id = "BO14";
                            break;
                    }
                    attributeList.put(Attribute.EVENT, event);
                    attributeList.put(Attribute.EVENT_ID, id);
                    OM_QUEUE.receive(new DataEnvelope(attributeList));
                }
            }
        }, 100, 100);

        // set up puller
        BufferReader puller = new BufferReader(OM_QUEUE, "OM Puller");
        Thread pullerThread = new Thread(puller, "Puller Thread");
        pullerThread.start();

        // add queue between puller and dispatcher
        Buffer buffer = new Buffer(new QueuedBufferStrategy<Envelope>());
        puller.connect(buffer);

        // set up dispatcher
        BufferReader dispatcher = new BufferReader(buffer, "Dispatcher");
        Thread dispatcherThread = new Thread(dispatcher, "Dispatcher Thread");
        dispatcherThread.start();

        // add router after dispatcher to distribute events
        Router router = new Router("Router", new RouteTable(), new AbstractRoutingStrategy() {
            public Object[] criteriaFromMessage(Message message) {
                return new Object[]{message.get(Attribute.EVENT_ID)};
            }
        });
        dispatcher.connect(router);

        // set order business objects
        Node book = new OrderBook("Order Book");
        book.connect(new EndPoint("End Point For Order Book"));

        Node marketPlace = new MarketPlace("Market Place");
        marketPlace.connect(new EndPoint("End Point For Market Place"));

        router.connect(book,"BO5");
        router.connect(marketPlace,new Object[]{"BO14", "BO36"});

        Node tradeBook = new TradeBook("Trade Book");
        router.connect(tradeBook, "BD6");
    }

    private static class EndPoint extends Node {
        private EndPoint(String name) {
            super(name);
        }

        @Override
        protected Message processData(Message message) {
            System.out.println(name + "->" + message);
            return super.processData(message);
        }
    }
}
