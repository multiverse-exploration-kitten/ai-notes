import pymongo
from pymongo import MongoClient
from bson.binary import Binary, UuidRepresentation
import uuid
import time

def insert_note():
    # Connect to MongoDB
    mongodb_uri = "mongodb://root:password@localhost:27017/ainotes_db"
    client = MongoClient(mongodb_uri, authSource="admin", uuidRepresentation='standard')

    db = client['ainotes_db']
    notes = db['notes']

    # Provided notebook_id and user_id
    notebook_id = uuid.UUID('123e4567-e89b-12d3-a456-426655440000')
    user_id = uuid.UUID('123e4567-e89b-12d3-a456-426614174000')
    note_id = uuid.UUID('1a85331e-c4e6-4c2b-8b66-375310cdb9f0')

    # Example note details
    title = "My First AI Note"
    content = "This note discusses the basics of AI, including definitions, history, and current applications."
    created_at = int(time.time() * 1000)  # Current time in milliseconds
    updated_at = int(time.time() * 1000)  # Current time in milliseconds

    note_document = {
        "_id": note_id,
        "userId": user_id,
        "notebookId": notebook_id,
        "title": title,
        "content": content,
        "createdAt": created_at,
        "updatedAt": updated_at
    }

    result = notes.insert_one(note_document)
    print(f"Inserted Note ID: {result.inserted_id}")

# Call the function to insert the note
insert_note()

