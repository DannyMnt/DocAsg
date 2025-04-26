import React, { useEffect, useState } from "react";

import "./App.css";

import StoryForm from "./components/StoryForm";

import StoryList from "./components/StoryList";

import DepartmentTabs from "./components/DepartmentTabs";

import { Story } from "./types/Story";

import {

  getAllStories,

  getStoriesByDepartment,

  createStory,

  updateStory,

  deleteStory,

} from "./services/storyService";



const App: React.FC = () => {

  const [stories, setStories] = useState<Story[]>([]);

  const [activeTab, setActiveTab] = useState<string>("All");

  const [editingStory, setEditingStory] = useState<Story | null>(null);

  const departments = ["IT", "Business", "Engineering"];



  useEffect(() => {

    fetchStories();

  }, [activeTab]);



  const fetchStories = async () => {

    try {

      let fetchedStories: Story[];

      if (activeTab === "All") {

        fetchedStories = await getAllStories();

      } else {

        fetchedStories = await getStoriesByDepartment(activeTab);

      }

      setStories(fetchedStories);

    } catch (error) {

      console.error("Error fetching stories:", error);

    }

  };



  const handleStorySubmit = async (story: Story) => {

    try {

      if (editingStory && editingStory.id) {

        await updateStory(editingStory.id, story);

      } else {

        await createStory(story);

      }

      setEditingStory(null);

      fetchStories();

    } catch (error) {

      console.error("Error saving story:", error);

    }

  };



  const handleEdit = (story: Story) => {

    setEditingStory(story);

  };



  const handleDelete = async (id: number) => {

    try {

      await deleteStory(id);

      fetchStories();

    } catch (error) {

      console.error("Error deleting story:", error);

    }

  };



  return (

    <div className="app">

      <header>

        <h1>VIA Tabloid</h1>

      </header>

      <main>

        <section className="story-form-section">

          <h2>{editingStory ? "Edit Story" : "Add New Story"}</h2>

          <StoryForm

            onSubmit={handleStorySubmit}

            initialStory={editingStory || undefined}

          />

          {editingStory && (

            <button onClick={() => setEditingStory(null)}>Cancel Editing</button>

          )}

        </section>

        <section className="story-display-section">

          <h2>Stories</h2>

          <DepartmentTabs

            departments={departments}

            activeTab={activeTab}

            onTabChange={setActiveTab}

          />

          <StoryList

            stories={stories}

            onEdit={handleEdit}

            onDelete={handleDelete}

          />

        </section>

      </main>

    </div>

  );

};



export default App;
