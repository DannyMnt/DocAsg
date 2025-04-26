import React, { useState } from "react";

import { Story } from "../types/Story";



interface StoryFormProps {

  onSubmit: (story: Story) => void;

  initialStory?: Story;

}



const StoryForm: React.FC<StoryFormProps> = ({

  onSubmit,

  initialStory = { title: "", content: "", department: "" },

}) => {

  const [story, setStory] = useState<Story>(initialStory);



  const handleChange = (

    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>

  ) => {

    const { name, value } = e.target;

    setStory({ ...story, [name]: value });

  };



  const handleSubmit = (e: React.FormEvent) => {

    e.preventDefault();

    onSubmit(story);

    if (!initialStory.id) {

      setStory({ title: "", content: "", department: "" });

    }

  };



  return (

    <form onSubmit={handleSubmit} className="story-form">

      <div className="form-group">

        <label htmlFor="title">Title</label>

        <input

          type="text"

          id="title"

          name="title"

          value={story.title}

          onChange={handleChange}

          required

        />

      </div>

      <div className="form-group">

        <label htmlFor="content">Content</label>

        <textarea

          id="content"

          name="content"

          value={story.content}

          onChange={handleChange}

          required

        />

      </div>

      <div className="form-group">

        <label htmlFor="department">Department</label>

        <select

          id="department"

          name="department"

          value={story.department}

          onChange={handleChange}

          required

        >

          <option value="">Select Department</option>

          <option value="IT">IT</option>

          <option value="Business">Business</option>

          <option value="Engineering">Engineering</option>

        </select>

      </div>

      <button type="submit">{initialStory.id ? "Update" : "Add"} Story</button>

    </form>

  );

};



export default StoryForm;
