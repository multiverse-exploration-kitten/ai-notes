import pymongo
from pymongo import MongoClient
from bson.binary import Binary, UuidRepresentation
import uuid
import time
import random

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

def generate_users_notes():
    # Connect to MongoDB
    mongodb_uri = "mongodb://root:password@localhost:27017/ainotes_db"
    client = MongoClient(mongodb_uri, authSource="admin", uuidRepresentation='standard')
    # Connect to Postgres

    userId = "123e4567-e89b-12d3-a456-426614174000"

    db = client['ainotes_db']
    users = db['users']
    notebooks = db['notebooks']
    notes = db['notes']

    # Generate random data
    for i in range(2):  # Two users
        user_id = uuid.uuid4()
        user_document = {
            "_id": user_id,
            "name": f"User{i+1}",
            "email": f"user{i+1}@example.com"
        }
        users.insert_one(user_document)

        for j in range(2):  # Two notebooks per user
            notebook_id = uuid.uuid4()
            notebook_document = {
                "_id": notebook_id,
                "userId": user_id,
                "name": f"Notebook{j+1}"
            }
            notebooks.insert_one(notebook_document)

            for k in range(3):  # Three notes per notebook
                note_id = uuid.uuid4()
                title = f"Note {k+1} of Notebook {j+1}"
                content = f"Content for {title}, part of {notebook_document['name']} owned by {user_document['name']}."
                created_at = int(time.time() * 1000)
                updated_at = int(time.time() * 1000)

                note_document = {
                    "_id": note_id,
                    "userId": user_id,
                    "notebookId": notebook_id,
                    "title": title,
                    "content": content,
                    "createdAt": created_at,
                    "updatedAt": updated_at
                }
                notes.insert_one(note_document)
                print(f"Inserted Note ID: {note_id}")

# Call the function to insert the note
# insert_note()
# generate_users_notes()
