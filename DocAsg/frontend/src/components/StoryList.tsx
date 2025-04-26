import React from "react";

import { Story } from "../types/Story";



interface StoryListProps {

  stories: Story[];

  onEdit: (story: Story) => void;

  onDelete: (id: number) => void;

}



const StoryList: React.FC<StoryListProps> = ({ stories, onEdit, onDelete }) => {

  return (

    <div className="story-list">

      {stories.length === 0 ? (

        <p>No stories available.</p>

      ) : (

        stories.map((story) => (

          <div key={story.id} className="story-card">

            <h3>{story.title}</h3>

            <p>{story.content}</p>

            <p className="department">Department: {story.department}</p>

            <div className="story-actions">

              <button onClick={() => onEdit(story)}>Edit</button>

              <button onClick={() => story.id && onDelete(story.id)}>Delete</button>

            </div>

          </div>

        ))

      )}

    </div>

  );

};



export default StoryList;
