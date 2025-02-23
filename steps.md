
# Create BD
docker run --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres

# Create database
```sql
    -- -----------------------------------------------------
    -- Schema db_events
    -- -----------------------------------------------------
    CREATE SCHEMA IF NOT EXISTS db_events;
    
    -- Set the search path to use the new schema
    SET search_path TO db_events;
    
    -- -----------------------------------------------------
    -- Table db_events.tbl_event
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS tbl_event (
    event_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    pretty_name VARCHAR(50) NOT NULL UNIQUE,
    location VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    start_date DATE,
    end_date DATE,
    start_time TIME,
    end_time TIME
    );
    
    -- -----------------------------------------------------
    -- Table db_events.tbl_user
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS tbl_user (
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(255),
    user_email VARCHAR(255)
    );
    
    -- -----------------------------------------------------
    -- Table db_events.tbl_subscription
    -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS tbl_subscription (
    subscription_number SERIAL PRIMARY KEY,
    subscribed_user_id INTEGER NOT NULL,
    indication_user_id INTEGER,
    event_id INTEGER NOT NULL,
    CONSTRAINT fk_tbl_subscription_tbl_user
    FOREIGN KEY (subscribed_user_id)
    REFERENCES tbl_user (user_id),
    CONSTRAINT fk_tbl_subscription_tbl_user1
    FOREIGN KEY (indication_user_id)
    REFERENCES tbl_user (user_id),
    CONSTRAINT fk_tbl_subscription_tbl_event1
    FOREIGN KEY (event_id)
    REFERENCES tbl_event (event_id)
    );
    
    -- Create indexes for foreign keys
    CREATE INDEX idx_subscription_subscribed_user ON tbl_subscription(subscribed_user_id);
    CREATE INDEX idx_subscription_indication_user ON tbl_subscription(indication_user_id);
    CREATE INDEX idx_subscription_event ON tbl_subscription(event_id);
```

# Example Request Body Event
```json
{
    "title": "Event 1",
    "pretty_name": "event1",
    "location": "Location 1",
    "price": 100.00,
    "start_date": "2021-10-01",
    "end_date": "2021-10-02",
    "start_time": "10:00:00",
    "end_time": "12:00:00"
}
```

