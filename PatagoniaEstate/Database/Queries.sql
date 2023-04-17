CREATE TABLE Area
(
  Area_Id INT NOT NULL,
  No_Properties INT NOT NULL,
  Location INT NOT NULL,
  PRIMARY KEY (Area_Id)
);

CREATE TABLE Admin
(
  ID INT NOT NULL,
  Name INT NOT NULL,
  Surname INT NOT NULL,
  Username INT NOT NULL,
  Password INT NOT NULL,
  Email INT NOT NULL,
  Address INT NOT NULL,
  Gender INT NOT NULL,
  Age INT NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE Auction
(
  Auction_ID INT NOT NULL,
  Auction_Date INT NOT NULL,
  Auction_Venue INT NOT NULL,
  Auction_InitialPrice INT NOT NULL,
  Auctioneer INT NOT NULL,
  PRIMARY KEY (Auction_ID)
);

CREATE TABLE User
(
  Name INT NOT NULL,
  Age INT NOT NULL,
  Email INT NOT NULL,
  Address INT NOT NULL,
  username INT NOT NULL,
  Password INT NOT NULL,
  Status INT NOT NULL,
  ID INT NOT NULL,
  Credit_Score INT NOT NULL,
  ID INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (ID) REFERENCES Admin(ID)
);

CREATE TABLE Agent
(
  Agent_ID INT NOT NULL,
  Salary INT NOT NULL,
  Name INT NOT NULL,
  Surname INT NOT NULL,
  Username INT NOT NULL,
  Password INT NOT NULL,
  Email INT NOT NULL,
  Address INT NOT NULL,
  Gender INT NOT NULL,
  Age INT NOT NULL,
  ID INT NOT NULL,
  ID INT,
  Auction_ID INT,
  PRIMARY KEY (Agent_ID),
  FOREIGN KEY (ID) REFERENCES User(ID),
  FOREIGN KEY (ID) REFERENCES Admin(ID),
  FOREIGN KEY (Auction_ID) REFERENCES Auction(Auction_ID)
);

CREATE TABLE Property
(
  Property_Id INT NOT NULL,
  Property_Name INT NOT NULL,
  Description INT NOT NULL,
  Nr_Bedrooms INT NOT NULL,
  Nr_Bathrooms INT NOT NULL,
  Area INT NOT NULL,
  Image INT NOT NULL,
  Price INT NOT NULL,
  No_Garages INT NOT NULL,
  Stories INT NOT NULL,
  Status INT NOT NULL,
  Agent_ID INT NOT NULL,
  Area_Id INT NOT NULL,
  ID INT,
  Auction_ID INT,
  PRIMARY KEY (Property_Id),
  FOREIGN KEY (Agent_ID) REFERENCES Agent(Agent_ID),
  FOREIGN KEY (Area_Id) REFERENCES Area(Area_Id),
  FOREIGN KEY (ID) REFERENCES Admin(ID),
  FOREIGN KEY (Auction_ID) REFERENCES Auction(Auction_ID)
);

CREATE TABLE Agent_Area
(
  RelationID INT NOT NULL,
  Agent_ID INT NOT NULL,
  Area_Id INT NOT NULL,
  PRIMARY KEY (Agent_ID, Area_Id),
  FOREIGN KEY (Agent_ID) REFERENCES Agent(Agent_ID),
  FOREIGN KEY (Area_Id) REFERENCES Area(Area_Id)
);

CREATE TABLE Appointment
(
  Date INT NOT NULL,
  Venue INT NOT NULL,
  ID INT NOT NULL,
  Agent_ID INT NOT NULL,
  PRIMARY KEY (Date),
  FOREIGN KEY (ID) REFERENCES User(ID),
  FOREIGN KEY (Agent_ID) REFERENCES Agent(Agent_ID),
  UNIQUE (ID, Agent_ID)
);

CREATE TABLE Participants
(
  ID INT NOT NULL,
  Auction_ID INT NOT NULL,
  PRIMARY KEY (ID, Auction_ID),
  FOREIGN KEY (ID) REFERENCES User(ID),
  FOREIGN KEY (Auction_ID) REFERENCES Auction(Auction_ID)
);